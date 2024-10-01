package com.tapiwa.nice.app.models;

import java.util.ArrayList;
import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

public class SystemMetrics {

    private String uptime;
    private int cpuCount;
    private double cpuUsage;
    private String totalMemory;
    private String availableMemory;
    private List<DiskInfo> diskInfo;
    private List<NetworkInfo> networkInfo;

    // Constructor with OSHI metrics fetching
    public SystemMetrics() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hal = systemInfo.getHardware();
        OperatingSystem os = systemInfo.getOperatingSystem();

        // Fetch uptime
        this.uptime = FormatUtil.formatElapsedSecs(os.getSystemUptime());

        // Fetch CPU count and usage
        CentralProcessor processor = hal.getProcessor();
        this.cpuCount = processor.getLogicalProcessorCount();
      

        // Fetch total and available memory
        GlobalMemory memory = hal.getMemory();
        this.totalMemory = FormatUtil.formatBytes(memory.getTotal());
        this.availableMemory = FormatUtil.formatBytes(memory.getAvailable());

        // Fetch disk information
        this.diskInfo = new ArrayList<>();
        for (OSFileStore fs : os.getFileSystem().getFileStores()) {
            this.diskInfo.add(new DiskInfo(
                    fs.getName(),
                    FormatUtil.formatBytes(fs.getTotalSpace()),
                    FormatUtil.formatBytes(fs.getUsableSpace())
            ));
        }

        // Fetch network information
        this.networkInfo = new ArrayList<>();
        hal.getNetworkIFs().forEach(networkIF -> {
            networkIF.updateAttributes();
            this.networkInfo.add(new NetworkInfo(
                    networkIF.getDisplayName(),
                    FormatUtil.formatBytes(networkIF.getBytesSent()),
                    FormatUtil.formatBytes(networkIF.getBytesRecv())
            ));
        });
    }

    // Getters only (no setters)
    public String getUptime() {
        return uptime;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public String getTotalMemory() {
        return totalMemory;
    }

    public String getAvailableMemory() {
        return availableMemory;
    }

    public List<DiskInfo> getDiskInfo() {
        return diskInfo;
    }

    public List<NetworkInfo> getNetworkInfo() {
        return networkInfo;
    }

    // Inner DTO classes for DiskInfo and NetworkInfo
    public static class DiskInfo {
        private String name;
        private String totalSpace;
        private String usedSpace;

        public DiskInfo(String name, String totalSpace, String usedSpace) {
            this.name = name;
            this.totalSpace = totalSpace;
            this.usedSpace = usedSpace;
        }

        public String getName() {
            return name;
        }

        public String getTotalSpace() {
            return totalSpace;
        }

        public String getUsedSpace() {
            return usedSpace;
        }
    }

    public static class NetworkInfo {
        private String displayName;
        private String bytesSent;
        private String bytesReceived;

        public NetworkInfo(String displayName, String bytesSent, String bytesReceived) {
            this.displayName = displayName;
            this.bytesSent = bytesSent;
            this.bytesReceived = bytesReceived;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getBytesSent() {
            return bytesSent;
        }

        public String getBytesReceived() {
            return bytesReceived;
        }
    }

    // Test to display the metrics
    public static void main(String[] args) {
        SystemMetrics metrics = new SystemMetrics();

        // Displaying the metrics
        System.out.println("Uptime: " + metrics.getUptime());
        System.out.println("CPU Count: " + metrics.getCpuCount());
        System.out.printf("CPU Usage: %.2f%%\n", metrics.getCpuUsage());
        System.out.println("Total Memory: " + metrics.getTotalMemory());
        System.out.println("Available Memory: " + metrics.getAvailableMemory());

        // Display disk info
        for (DiskInfo disk : metrics.getDiskInfo()) {
            System.out.println("Disk Name: " + disk.getName());
            System.out.println("Total Space: " + disk.getTotalSpace());
            System.out.println("Used Space: " + disk.getUsedSpace());
        }

        // Display network info
        for (NetworkInfo net : metrics.getNetworkInfo()) {
            System.out.println("Network Interface: " + net.getDisplayName());
            System.out.println("Bytes Sent: " + net.getBytesSent());
            System.out.println("Bytes Received: " + net.getBytesReceived());
        }
    }
}
