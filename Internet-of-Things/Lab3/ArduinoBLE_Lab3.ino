#include <ArduinoBLE.h>
#include <Arduino_HTS221.h>
#include "TimeoutTimer.h"
#define BUFSIZE 20

// BLE services and characteristics
BLEService uartService("6E400001-B5A3-F393-E0A9-E50E24DCCA9E");
BLEStringCharacteristic txChar("6E400002-B5A3-F393-E0A9-E50E24DCCA9E", BLEWrite, 20);
BLEStringCharacteristic rxChar("6E400003-B5A3-F393-E0A9-E50E24DCCA9E", BLERead | BLENotify, 20);
BLEService essService("181A");
BLEShortCharacteristic tempChar("2A6E", BLERead | BLENotify);

void setup() {
  Serial.begin(9600);
  while (!Serial);

  if (!BLE.begin()) {
    Serial.println("Starting BLE failed!");
    while (1);
  }

  if (!HTS.begin()) {
    Serial.println("Failed to initialize humidity temperature sensor!");
    while (1);
  }

  String deviceAddress = BLE.address();
  BLE.setLocalName("ArduinoBLE Lab3");
  BLE.setAdvertisedService(uartService);
  uartService.addCharacteristic(txChar);
  uartService.addCharacteristic(rxChar);
  BLE.addService(uartService);
  essService.addCharacteristic(tempChar);
  BLE.addService(essService);
  BLE.advertise();
  Serial.println("Bluetooth device (" + deviceAddress + ") active, waiting for connections...");
}

int val = 1;

void loop() {
  BLEDevice central = BLE.central();

  if (central) {
    Serial.print("Connected to central: ");
    Serial.println(central.address());

    while (central.connected()) {
      char inputs[BUFSIZE + 1];
      if (getUserInput(inputs, BUFSIZE)) {
        Serial.print("[Send] ");
        Serial.println(inputs);
        rxChar.writeValue(inputs);
      }

      if (txChar.written()) {
        Serial.print("[Recv] ");
        Serial.println(txChar.value());
        val = txChar.value().toInt();
      }

      float temp = HTS.readTemperature(); // Read temperature from the HTS221 sensor
      Serial.print("Temp: ");
      Serial.println(temp);

      short shortTemp = (short)(temp * 100); // Adjusted to short format
      tempChar.writeValue(shortTemp);

      delay(val * 1000);
    }

    Serial.print("Disconnected from central: ");
    Serial.println(central.address());
  }
}

bool getUserInput(char buffer[], uint8_t maxSize) {
  TimeoutTimer timeout(100);
  memset(buffer, 0, maxSize);
  while ((!Serial.available()) && !timeout.expired()) {
    delay(1);
  }

  if (timeout.expired()) return false;

  delay(2);
  uint8_t count = 0;
  do {
    count += Serial.readBytes(buffer + count, maxSize);
    delay(2);
  } while ((count < maxSize) && (Serial.available()));

  return true;
}