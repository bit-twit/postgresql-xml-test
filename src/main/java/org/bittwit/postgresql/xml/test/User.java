package org.bittwit.postgresql.xml.test;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="user_test_table")
@SqlResultSetMapping(name="GetUserDtoByPayloadPropertySexQuery",
    classes={
        @ConstructorResult(targetClass=UserDto.class, columns = { 
            @ColumnResult(name="name"),
            @ColumnResult(name="sex")})
    })
public class User implements Serializable {

    private static final long serialVersionUID = 3139895201814574652L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Type(type="org.bittwit.postgresql.xml.test.PostgreSQLXmlType")
    private String payload;
    @ElementCollection
    @CollectionTable(name="users_x_partners_test_table", 
        joinColumns={@JoinColumn(name="user_id")})
    @Column(name="partner_id")
    private Set<Long> partnerIds;

    public User () {
        
    }

    public User (Long id, String name, String payload) {
        this.id = id;
        this.name = name;
        this.payload = payload;
    }

    public User (Long id, String name, String payload, Set<Long> partnerIds) {
        this.id = id;
        this.name = name;
        this.payload = payload;
        this.partnerIds = new TreeSet<Long>(partnerIds);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Set<Long> getPartnerIds() {
        return partnerIds;
    }

    public void setPartnerIds(Set<Long> partnerIds) {
        this.partnerIds = partnerIds;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", payload=" + payload + "]";
    }

}
