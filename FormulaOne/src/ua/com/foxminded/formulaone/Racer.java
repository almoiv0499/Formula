package ua.com.foxminded.formulaone;

public class Racer {

    private String abbreviation;
    private String nameRacer;
    private String carTeam;
    private String lapTime;

    public Racer(String abbreviation, String nameRacer, String carTeam) {
        this.abbreviation = abbreviation;
        this.nameRacer = nameRacer;
        this.carTeam = carTeam;
    }

    public String getLapTime() {
        return lapTime;
    }

    public void setLapTime(String lapTime) {
        this.lapTime = lapTime;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getNameRacer() {
        return nameRacer;
    }

    public String getCarTeam() {
        return carTeam;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((abbreviation == null) ? 0 : abbreviation.hashCode());
        result = prime * result + ((carTeam == null) ? 0 : carTeam.hashCode());
        result = prime * result + ((lapTime == null) ? 0 : lapTime.hashCode());
        result = prime * result + ((nameRacer == null) ? 0 : nameRacer.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Racer other = (Racer) obj;
        if (abbreviation == null) {
            if (other.abbreviation != null)
                return false;
        } else if (!abbreviation.equals(other.abbreviation))
            return false;
        if (carTeam == null) {
            if (other.carTeam != null)
                return false;
        } else if (!carTeam.equals(other.carTeam))
            return false;
        if (lapTime == null) {
            if (other.lapTime != null)
                return false;
        } else if (!lapTime.equals(other.lapTime))
            return false;
        if (nameRacer == null) {
            if (other.nameRacer != null)
                return false;
        } else if (!nameRacer.equals(other.nameRacer))
            return false;
        return true;
    }

}

