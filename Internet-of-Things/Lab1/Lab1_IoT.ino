#include <Arduino_HTS221.h>
#include <Arduino_LSM9DS1.h>

// Declare variables for averages outside of loop() to maintain their state
float tempTotal = 0, humTotal = 0, yawTotal = 0, pitchTotal = 0, rollTotal = 0;
int readingsCount = 0; // Keep track of the number of readings

void setup() {
  Serial.begin(9600);
  if (!HTS.begin()) {
    Serial.println("Failed to initialize the humidity and temperature sensor!");
    while (1);
  }
  if (!IMU.begin()) {
    Serial.println("Failed to initialize IMU!");
    while (1);
  }
}

void loop() {
  float temperature = HTS.readTemperature();
  float humidity = HTS.readHumidity();

  float yaw, pitch, roll;
  if (IMU.readGyroscope(yaw, pitch, roll)) {
    tempTotal += temperature;
    humTotal += humidity;
    yawTotal += yaw;
    pitchTotal += pitch;
    rollTotal += roll;
    readingsCount++;
  }

  if (readingsCount >= 10) {
    Serial.print("Temperature Average: "); Serial.println(tempTotal / readingsCount);
    Serial.print("Humidity Average: "); Serial.println(humTotal / readingsCount);
    Serial.print("Yaw Average: "); Serial.println(yawTotal / readingsCount);
    Serial.print("Pitch Average: "); Serial.println(pitchTotal / readingsCount);
    Serial.print("Roll Average: "); Serial.println(rollTotal / readingsCount);
    tempTotal = 0;
    humTotal = 0;
    yawTotal = 0;
    pitchTotal = 0;
    rollTotal = 0;
    readingsCount = 0;
  }
  Serial.print("Temperature: "); Serial.print(temperature); Serial.println(" C");
  Serial.print("Humidity: "); Serial.print(humidity); Serial.println(" %");
  Serial.print("Gyro Yaw: "); Serial.print(yaw); Serial.println(" dps");
  Serial.print("Gyro Pitch: "); Serial.print(pitch); Serial.println(" dps");
  Serial.print("Gyro Roll: "); Serial.print(roll); Serial.println(" dps");
  
  delay(1000);
}
