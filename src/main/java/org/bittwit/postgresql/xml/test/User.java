package org.bittwit.postgresql.xml.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

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
    @Column(name="user_id")
    private Long userId;
    @Column(name="order_id")
    private Long orderId;
    @Column(name="partner_id")
    private Long partnerId;
    private String name;
    @OneToMany(fetch=FetchType.LAZY,
            cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH},
            mappedBy="user")
    private List<Payload> payload;

    public User () {
    }

    public User (String name, Long orderId, Long partnerId) {
        this.name = name;
        this.orderId = orderId;
        this.partnerId = partnerId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public Payload getPayload() {
        return payload != null ? payload.get(0) : null;
    }

    public void setPayload(Payload payload) {
        this.payload = new ArrayList<Payload>();
        this.payload.add(payload);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + "]";
    }

}
