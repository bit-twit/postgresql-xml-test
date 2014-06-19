package org.bittwit.postgresql.xml.test;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Type;

@Entity
public class Payload implements Serializable {
    private static final long serialVersionUID = 3988263845365540680L;

    @OneToOne(fetch=FetchType.LAZY, mappedBy="payload", orphanRemoval=true)
    @PrimaryKeyJoinColumn
    @Id
    private User user;

    @Type(type="org.bittwit.postgresql.xml.test.PostgreSQLXmlType")
    private String payload;

    public Payload () {
        
    }

    public Payload (User u, String payload) {
        this.user = u;
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        Payload other = (Payload) obj;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }
}
