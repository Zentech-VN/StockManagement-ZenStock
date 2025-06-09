package entity;

import java.util.Objects;

public class PermGroup {

    private int manhomquyen;
    private String tennhomquyen;

    public PermGroup() {
    }

    public PermGroup(int manhomquyen, String tennhomquyen) {
        this.manhomquyen = manhomquyen;
        this.tennhomquyen = tennhomquyen;
    }

    public int getManhomquyen() {
        return manhomquyen;
    }

    public void setManhomquyen(int manhomquyen) {
        this.manhomquyen = manhomquyen;
    }

    public String getTennhomquyen() {
        return tennhomquyen;
    }

    public void setTennhomquyen(String tennhomquyen) {
        this.tennhomquyen = tennhomquyen;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.manhomquyen;
        hash = 37 * hash + Objects.hashCode(this.tennhomquyen);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PermGroup other = (PermGroup) obj;
        if (this.manhomquyen != other.manhomquyen) {
            return false;
        }
        return Objects.equals(this.tennhomquyen, other.tennhomquyen);
    }

    @Override
    public String toString() {
        return "PermGroup{" + "manhomquyen=" + manhomquyen + ", tennhomquyen=" + tennhomquyen + '}';
    }
}
