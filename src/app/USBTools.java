package app;


import java.util.ArrayList;
import java.util.List;

import javax.usb.UsbConfiguration;
import javax.usb.UsbDevice;
import javax.usb.UsbEndpoint;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.UsbInterface;
import javax.usb.UsbPort;
import javax.usb.UsbServices;

import app.UsbDeviceList;


public class USBTools {
	private UsbServices theServices;
	private UsbHub theRootHub;
	private UsbDeviceList theDeviceList;
	
	public USBTools() {
		initialization();
	}
	
	public void initialization() {
		try {
			// get the UsbServices
			System.out.println("Loading UsbServices...");
			this.theServices = UsbHostManager.getUsbServices();
			this.theRootHub = this.theServices.getRootUsbHub();
			showServices();
		} catch(UsbException e) {
			System.out.println("Exception thrown  :" + e);
		}
		
		if(this.theRootHub.isRootUsbHub()) {
			// get the DeviceList
			System.out.println("Root Hub:\n" + this.theRootHub);
			this.theDeviceList = new UsbDeviceList(this.theRootHub);
			showAllDevices();
		} else {
			System.out.println("No Root Hub Found!");
		}
	}
	
	public void showServices() {
		// print info of the UsbServices and the RootUsbHub
		System.out.println("USB Service Implementation: " + this.theServices.getImpDescription());
		System.out.println("Implementation version: " + this.theServices.getImpVersion());
		System.out.println("Service API version: " + this.theServices.getApiVersion());
		System.out.println();
	}

	public void showAllDevices() {
		// print all devices in theDeviceList
		this.theDeviceList.showAll();
	}
	
	public void showCurrentDevice() {
		// print the current device in theDeviceList
		this.theDeviceList.showCurrent();
	}
	
	public void refreshDeviceList() {
		// refresh theDeviceList
		this.theDeviceList.refresh(this.theRootHub);
	}
	
	public void selectDevice(final int index) {
		// set the current device
		this.theDeviceList.select(index);
	}

	public UsbDevice getCurrentDevice() {
		// return the current device
		return this.theDeviceList.getCurrent();
	}
	
	public ArrayList<UsbDevice> getDeviceList() {
		return this.theDeviceList.getList();
	}
	
	
	
	
	
	
	
	
}
// end class USBTools





















