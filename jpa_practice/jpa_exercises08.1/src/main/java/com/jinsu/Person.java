package com.jinsu;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "person_phone_numbers", joinColumns = @JoinColumn(name = "person_id"))
    private Set<PhoneNumber> phoneNumbers = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "person_hobbies", joinColumns = @JoinColumn(name = "person_id"))
    @Column(name = "hobby")
    private List<String> hobbies = new ArrayList<>();
}
