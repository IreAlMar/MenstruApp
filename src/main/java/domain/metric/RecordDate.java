package domain.metric;

import java.util.Date;
import java.util.Objects;

public class RecordDate {
    private Date date;

    private RecordDate(Date date) {
        this.date = date;
    }

    public static RecordDate of(Date date){
//        check no future date
        return new RecordDate(date);
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordDate that = (RecordDate) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
