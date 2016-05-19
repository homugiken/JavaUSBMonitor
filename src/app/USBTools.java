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

import app.USBDeviceLList;


public class USBTools {
	private UsbServices theServices;
	private UsbHub theRootHub;
	private USBDeviceLList theDeviceList;
	
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
		
		// get the DeviceList
		this.theDeviceList = new USBDeviceLList(this.theRootHub);
		showAllDevices();
	}
	
	public void showServices() {
		// print info of the UsbServices and the RootUsbHub
		System.out.println("USB Service Implementation: " + this.theServices.getImpDescription());
		System.out.println("Implementation version: " + this.theServices.getImpVersion());
		System.out.println("Service API version: " + this.theServices.getApiVersion());
		System.out.println();
	}

	public void showAllDevices() {
		this.theDeviceList.showAll();
	}
	
	public void showCurrentDevice() {
		this.theDeviceList.showCurrent();
	}
	
	public void refreshDeviceList() {
		this.theDeviceList.refresh(this.theRootHub);
	}
	
	public void selectDevice(final int index) {
		this.theDeviceList.select(index);
	}

	public UsbDevice getCurrentDevice() {
		return this.theDeviceList.getCurrent();
	}
	
	
	
	
	
	
	
	
	
	
}
// end class USBTools





















