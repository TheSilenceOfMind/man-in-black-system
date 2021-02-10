package com.itmo.mibsystem.model.distribute.technology;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "mib_types_contract")
@Getter
@Setter
public class TypeContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_contract_id")
    private Long typeContractId;
    @Column(name = "type")
    private String type;

    public TypeContract() {
        typeContractId = null;
        type = "";
    }

    public TypeContract(String type) {
        typeContractId = null;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TypeContract document = (TypeContract) o;
        return Objects.equal(typeContractId, document.typeContractId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(typeContractId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TypeContract.class.getSimpleName() + "[", "]")
                .add("type_contract_id=" + typeContractId)
                .add("type=" + type)
                .toString();
    }
}
