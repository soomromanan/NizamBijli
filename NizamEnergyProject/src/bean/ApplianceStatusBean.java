package bean;

public class ApplianceStatusBean {
    
    private int statusId;
    private int applianceId;
    private double latitude;
    private double longitude;
    private String statusDate;
    private double temperature;
    private int signalStrength;
    private String IMEI;
    private boolean lid;
    private double bv;
    private double ba;
    private double sv;
    private double sa;
    private double lv;
    private double la;
    private double volatge;
    private double backup;
    private String environment;
    private int type;

    public ApplianceStatusBean() {}

    public ApplianceStatusBean(int statusId, int applianceId, double volatge, double backup, double temperature, String environment, double latitude, double longitude, String statusDate) {
        this.statusId = statusId;
        this.applianceId = applianceId;
        this.volatge = volatge;
        this.backup = backup;
        this.temperature = temperature;
        this.environment = environment;
        this.latitude = latitude;
        this.longitude = longitude;
        this.statusDate = statusDate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getApplianceId() {
        return applianceId;
    }

    public void setApplianceId(int applianceId) {
        this.applianceId = applianceId;
    }

    public double getVolatge() {
        return volatge;
    }

    public void setVolatge(double volatge) {
        this.volatge = volatge;
    }

    public double getBackup() {
        return backup;
    }

    public void setBackup(double backup) {
        this.backup = backup;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSignalStrength() {
		return signalStrength;
	}

	public void setSignalStrength(int signalStrength) {
		this.signalStrength = signalStrength;
	}

	public String getIMEI() {
		return IMEI;
	}

	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}

	public boolean isLid() {
		return lid;
	}

	public void setLid(boolean lid) {
		this.lid = lid;
	}

	public double getBv() {
		return bv;
	}

	public void setBv(double bv) {
		this.bv = bv;
	}

	public double getBa() {
		return ba;
	}

	public void setBa(double ba) {
		this.ba = ba;
	}

	public double getSv() {
		return sv;
	}

	public void setSv(double sv) {
		this.sv = sv;
	}

	public double getSa() {
		return sa;
	}

	public void setSa(double sa) {
		this.sa = sa;
	}

	public double getLv() {
		return lv;
	}

	public void setLv(double lv) {
		this.lv = lv;
	}

	public double getLa() {
		return la;
	}

	public void setLa(double la) {
		this.la = la;
	}

	
   
    
}
