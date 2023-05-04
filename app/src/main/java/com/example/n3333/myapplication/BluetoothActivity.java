package com.example.n3333.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.util.Log;


public class BluetoothActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        initBTLollipop();
    }


    private void initBTLollipop() {
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled() || !getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            // Bluetooth is not supported on this device or not enabled
            Log.i("TAG", "checking is not bluetooth contain BLE");
            return;
        }

        BluetoothLeScanner scanner = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            scanner = BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            scanner.startScan(new ScanCallback() {
                @Override
                public void onScanResult(int callbackType, ScanResult result) {
                    // Device discovered, add it to a list

                    Log.i("TAG", "checking bluetooth onScanResult = " + result);
                }

                @Override
                public void onScanFailed(int errorCode) {
                    // Scan failed, handle error
                    Log.i("TAG", "checking bluetooth onScanFailed = " + errorCode);
                }
            });
        }

        BluetoothDevice device = bluetoothAdapter.getRemoteDevice("device_address");
        BluetoothGatt gatt = device.connectGatt(this, false, new BluetoothGattCallback() {
            @Override
            public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                if (newState == BluetoothProfile.STATE_CONNECTED) {
                    // Connection established, discover services
                    gatt.discoverServices();
                    Log.i("TAG", "checking onConnectionStateChange STATE_CONNECTED = " +     gatt.discoverServices());
                } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                    // Connection lost, handle error
                    Log.i("TAG", "checking onConnectionStateChange STATE_DISCONNECTED = ");
                }
            }

            @Override
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                // Services discovered, can now read/write characteristics
                Log.i("TAG", "checking onServicesDiscovered status = " + status);
            }

            @Override
            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                // Data received from device, handle it
                Log.i("TAG", "checking onCharacteristicChanged characteristic = " + characteristic);
            }
        });


    }
}