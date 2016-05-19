
package app;


import java.util.ArrayList;
import java.util.List;

import javax.usb.UsbDevice;
import javax.usb.UsbHub;


public class USBDeviceLList {
	private ArrayList<UsbDevice> theList;
	private int current;
	
	public USBDeviceLList() {
		this.theList = new ArrayList<UsbDevice>();
		this.current = -1;
	}
	
	public USBDeviceLList(final UsbHub hub) {
		this.theList = new ArrayList<UsbDevice>();
		this.current = -1;
		refresh(hub);
	}
	
    public void clear() {
        // clear deviceList to empty
        this.theList.clear();
        this.theList.trimToSize();
        this.current = -1;
        System.out.println("DeviceList cleared.");
    }
    
    private boolean checkIndex(final int index) {
    	if((index >= 0) && (index < this.theList.size())) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public UsbDevice get(final int index) {
    	if(checkIndex(index)) {
    		return this.theList.get(index);
    	} else {
    		return null;
    	}
    }
	
    public ArrayList<UsbDevice> getList() {
    	if(this.theList.isEmpty()) {
    		System.out.println("DeviceList is Empty!");
    		return null;
    	} else {
    		return this.theList;
    	}
    }
	
    public UsbDevice getCurrent() {
    	if(checkIndex(this.current)) {
    		return get(this.current);
    	} else {
    		System.out.println("None");
    		return null;
    	}
    }
	
	public void refresh(final UsbHub hub) {
        clear();
        findAll(hub);
        this.theList.trimToSize();
        System.out.println("DeviceList Refreshed.");
	}
	
	private void findAll(final UsbHub hub) {
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
		if(checkIndex(index)) {
			this.current = index;
		} else {
			System.out.println("Invalid Index for DeviceList");
		}
	}

	public void showCurrent() {
		if(checkIndex(this.current)) {
			System.out.println("Current Device:");
			System.out.printf("%04d*", this.current);
			System.out.println(getCurrent());
		} else {
			System.out.println("None");
		}
	}
	
	public void showAll() {
		System.out.println("Device List");
		if(this.theList.size() > 0) {
			for(int i = 0; checkIndex(i); i++) {
				if(i == this.current) {
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
		this.theList.trimToSize();
		if(this.theList.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
// end class USBDeviceLList
