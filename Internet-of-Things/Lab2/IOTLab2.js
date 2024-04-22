var firebase = require('firebase/app');
var nodeimu = require('@trbll/nodeimu');
var IMU = new nodeimu.IMU();
var sense = require('@trbll/sense-hat-led');
const { getDatabase, ref, onValue, set, update, get, child } = require('firebase/database');
const firebaseConfig = {
    apiKey: "AIzaSyDCyjSkD2N6uivPf07yIYLoGo_WlNB_xrU",
    authDomain: "iotlab2-ceac6.firebaseapp.com",
    projectId: "iotlab2-ceac6",
    storageBucket: "iotlab2-ceac6.appspot.com",
    messagingSenderId: "207738819633",
    appId: "1:207738819633:web:8dd0c0d64d2aa64c07b110",
    measurementId: "G-20Q2J27FQH"
};
firebase.initializeApp(firebaseConfig);
const database = getDatabase();
set(ref(database, 'iotLab2'), {
    temperature: 0,
    humidity: 0,
    update_light: false,
    light_info: {
        light_r: 0,
        light_g: 0,
        light_b: 0,
        light_row: 0,
        light_col: 0
    }
});
onValue(ref(database, 'iotLab2' + "/update_light"), async (snapshot) => {
    const data = snapshot.val();
    if (data) {
        const values = await getLightInfo();
        console.log('Row ' + values.light_row + ' Column ' + values.light_col + ' Changed to values R:' +values.light_r +' G:'+ values.light_g + ' B:' + values.light_b )
        sense.setPixel(values.light_row, values.light_col, [values.light_r, values.light_g, values.light_b]);
    }
})
async function getLightInfo() {
    //console.log("TEST1");
    try {
        const snapshot = await get(child(ref(database), 'iotLab2/light_info'));
        if (snapshot.exists()) {
            return snapshot.val();
        } else {
            return false;
        }
    } catch (error) {
        console.error(error);
        return false;
    }
}
setInterval(sendData, 5000);
function sendData() {
    var callb = function (e, data) {
        if (e) {
            console.log(e);
            return;
        }

        if (data.temperature && data.humidity) {
            const temp = data.temperature;
            const humidity = data.humidity;
            console.log("Humidity "+ humidity);
            console.log("Temperature "+ temp);
            const updates = { 'iotLab2/humidity': humidity, 'iotLab2/temperature': temp }
            return update(ref(database), updates);
        }
    }
    IMU.getValue(callb);
}
