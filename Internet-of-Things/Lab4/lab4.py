#!/usr/bin/env python3
import paho.mqtt.client as mqtt
import json
import time
import pprint
from influxdb_client import InfluxDBClient, Point
from influxdb_client.client.write_api import SYNCHRONOUS

def on_connect(client, userdata, flags, rc):
    print("Connected with result code "+str(rc))
    client.subscribe("uiowa/iot/lab4/#")

def on_message(client, userdata, msg):
    print("Received a message on topic: " + msg.topic)
    m_decode=str(msg.payload.decode("utf-8", "ignore"))
    print("type of decoded message payload is: " + str(type(m_decode)))
    msg_payload = json.loads(m_decode)
    print("Type of msg_payload after json.loads() is: " + str(type(msg_payload)))
    print("Contents of message payload: ")
    pp = pprint.PrettyPrinter(indent=2)
    pp.pprint(msg_payload)

    measurement = msg.topic.split("/")[-1]
    payload = {
        "measurement": measurement,
        "fields": {
            "m_decode": m_decode,
            "msg_payload": msg_payload
        }
    }
    influx_write_api.write(bucket=bucket, record=[payload])

bucket = "lab4"
url = "http://localhost:8086"
org = "iowa"
token = "Sh0tcsBi5278q6aqCWaOJz4wiLnEsOfSE6xsqLgthLxV4waHuAT2CimiKst2XMqc8FnHqo1xV-Onf22y7VNN2g=="

influx_client = InfluxDBClient(url=url, token=token, org=org)
influx_write_api = influx_client.write_api(write_options=SYNCHRONOUS)

while(connOK == False):
mqtt_client = mqtt.Client(mqtt.CallbackAPIVersion.VERSION1)
mqtt_client.on_connect = on_connect
mqtt_client.on_message = on_message
connOK=False
while(connOK == False):
    try:
        mqtt_client.connect("broker.hivemq.com", 1883, 60)
        connOK = True
    except:
        connOK = False
    time.sleep(1)

# Blocking loop to the Mosquitto broker
mqtt_client.loop_forever()
