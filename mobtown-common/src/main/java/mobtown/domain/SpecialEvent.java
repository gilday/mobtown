package mobtown.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "SPECIAL_EVENT")
public class SpecialEvent {

    @Id
    @NotNull
    @Column(name = "ID", nullable = false)
    private String id;

    @ElementCollection
    @CollectionTable(
            name = "ARREST",
            joinColumns = { @JoinColumn(name = "SE_ID") },
            foreignKey = @ForeignKey(name = "FK_ARREST_SPECIAL_EVENT"))
    @OrderBy("TIMESTAMP DESC")
    private List<Arrest> arrests = new ArrayList<>();

    @NotNull
    @NotEmptyString
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "TYPE")
    private String type;

    @NotNull
    @Column(name = "START_TIME", nullable = false)
    private LocalDateTime start;

    @NotNull
    @Column(name = "END_TIME", nullable = false)
    private LocalDateTime end;

    /**
     * no-args ctor for JPA
     */
    protected SpecialEvent() { }

    public SpecialEvent(
            final String id,
            final String name,
            final String type,
            final LocalDateTime start,
            final LocalDateTime end) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.start = start;
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public List<Arrest> getArrests() {
        return Collections.unmodifiableList(arrests);
    }

    public void addArrest(final String intersection,
                          final String neighborhood,
                          final LocalDateTime timestamp,
                          final String location) {
        final Arrest arrest = new Arrest(intersection, neighborhood, timestamp, location);
        arrests.add(arrest);
    }

    public void removeArrest(final Arrest arrest) {
        arrests.remove(arrest);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "SpecialEvent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
