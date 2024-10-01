package com.tapiwa.nice.app.models;

import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

public class SystemMetrics{

    private Long upTime;

    private final  SystemInfo systemInfo = new SystemInfo();    
    private final OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
    public SystemMetrics(){

        this.upTime = operatingSystem.getSystemUptime();
    }

    public Long getUpTime(){

        return operatingSystem.getSystemUptime();
    }

}