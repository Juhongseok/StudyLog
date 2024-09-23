package com.jhs.dynamictable.history.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "history")
@NoArgsConstructor(access = PROTECTED)
public class History {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    public History(String content) {
        this.content = content;
    }

}
