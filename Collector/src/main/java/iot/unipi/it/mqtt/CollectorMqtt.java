package iot.unipi.it.mqtt;

import org.eclipse.paho.client.mqttv3.*;

public class CollectorMqtt implements MqttCallback {
    public CollectorMqtt() {
        String topic        = "temperature";
        String broker       = "tcp://127.0.0.1:1883";
        String clientId     = "Collector";

        try {
            MqttClient mqttClient = new MqttClient(broker, clientId);
            System.out.println("Connecting to broker: " + broker);
            mqttClient.setCallback(this);
            mqttClient.connect();
            mqttClient.subscribe(topic);
        }catch (MqttException me){me.printStackTrace();}
    }

    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost cause: " + cause.getCause().getMessage());
    }

    public void messageArrived(String topic, MqttMessage message) {
        System.out.println(String.format("[%s] %s", topic, new String(message.getPayload())));
        // Messaggio arrivato sul topic temperature, salvataggio della temperatura nel DB
        // Controllo sulla temperatura / possibile messaggio all attutatore?
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        try {
            System.out.println(new String(token.getMessage().getPayload()));
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        CollectorMqtt mc = new CollectorMqtt();
    }
}
