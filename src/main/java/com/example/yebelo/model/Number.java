package com.example.yebelo.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
@Entity
//@NamedNativeQueries({
//        @NamedNativeQuery(
//                name = "FindByCategoryCode",
//                query = "SELECT id, firstName, lastName, email, department.id, department.name " +
//                        "FROM employee, department",
//                resultClass=EmployeeEntity.class
//        )
//})
public class Number {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CategoryCode;
    private int OldValue;
    private int NewValue;
}
