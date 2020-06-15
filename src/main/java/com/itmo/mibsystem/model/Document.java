package com.itmo.mibsystem.model;

import com.google.common.base.Objects;
import java.util.StringJoiner;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mib_document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "category1")
    private String category1;
    @Column(name = "category2")
    private String category2;
    @Column(name = "category3")
    private String category3;
    @Column(name = "s_field1")
    private String field1;
    @Column(name = "s_field2")
    private String field2;
    @Column(name = "s_field3")
    private String field3;
    @Column(name = "s_description")
    private String description;

    public Document() {
    }

    public Document(String category1, String category2, String category3,
        String field1, String field2, String field3, String description) {
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Document document = (Document) o;
        return Objects.equal(id, document.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Document.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("category1='" + category1 + "'")
            .add("category2='" + category2 + "'")
            .add("category3='" + category3 + "'")
            .add("field1='" + field1 + "'")
            .add("field2='" + field2 + "'")
            .add("field3='" + field3 + "'")
            .add("description='" + description + "'")
            .toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
