package app;


import java.util.ArrayList;
import java.util.List;

import javax.usb.UsbDevice;
import javax.usb.UsbHub;


public class UsbDeviceList {
	private ArrayList<UsbDevice> theList;
	private int currentIndex;
	
	public UsbDeviceList() {
		this.theList = new ArrayList<UsbDevice>();
		this.currentIndex = -1;
	}
	
	public UsbDeviceList(final UsbHub rootHub) {
		this.theList = new ArrayList<UsbDevice>();
		this.currentIndex = -1;
		refresh(rootHub);
	}
	
    public void clear() {
        // clear deviceList to empty
        this.theList.clear();
        this.theList.trimToSize();
        this.currentIndex = -1;
        System.out.println("DeviceList cleared.");
    }
    
    private boolean checkIndex(final int index) {
    	// check if the index is within the range of theList
    	if((index >= 0) && (index < this.theList.size())) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public UsbDevice get(final int index) {
    	// return the device at the index of theList
    	if(checkIndex(index)) {
    		return this.theList.get(index);
    	} else {
    		System.out.println("None");
    		return null;
    	}
    }
	
    public ArrayList<UsbDevice> getList() {
    	// return theList
    	if(this.theList.isEmpty()) {
    		return null;
    	} else {
    		return this.theList;
    	}
    }
	
    public UsbDevice getCurrent() {
    	// return the device at the currentIndex of theList
    	if(checkIndex(this.currentIndex)) {
    		return get(this.currentIndex);
    	} else {
    		System.out.println("None");
    		return null;
    	}
    }
	
	public void refresh(final UsbHub rootHub) {
		// clear then load all devices to theList
        clear();
        findAll(rootHub);
        this.theList.trimToSize();
        System.out.println("DeviceList Refreshed.");
	}
	
	private void findAll(final UsbHub hub) {
		// find and load all devices to theList
		this.theList.add((UsbDevice) hub);
		for(UsbDevice device: (List<UsbDevice>) hub.getAttachedUsbDevices()) {
			if(device.isUsbHub()) {
				findAll((UsbHub) device);
			} else {
				this.theList.add(device);
			}
		}
	}
	
	public void select(final int index) {
		// set the currentIndex
		if(checkIndex(index)) {
			this.currentIndex = index;
		} else {
			System.out.println("Invalid Index for DeviceList");
		}
	}

	public void showCurrent() {
		// print info of the current device
		if(checkIndex(this.currentIndex)) {
			System.out.println("currentIndex Device:");
			System.out.printf("%04d*", this.currentIndex);
			System.out.println(getCurrent());
		} else {
			System.out.println("None");
		}
	}
	
	public void showAll() {
		// print info of all the devices in theList
		System.out.println("Device List");
		if(this.theList.size() > 0) {
			for(int i = 0; checkIndex(i); i++) {
				if(i == this.currentIndex) {
					System.out.printf("%04d*", i);
				} else {
					System.out.printf("%04d ", i);
				}
				System.out.println(get(i));
			}
		} else {
			System.out.println("None");
		}
	}
	
	public boolean isEmpty() {
		// check if theList is empty
		this.theList.trimToSize();
		if(this.theList.size() == 0) {
			System.out.println("DeviceList is Empty!");
			return true;
		} else {
			return false;
		}
	}
}
// end class USBDeviceLList
