package com.favoritebookmarks.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="bookmark")
public class Bookmark
{
    @Id
    @Column(name="id", nullable=false, updatable=false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    
    @NotNull
    @Column(name="name", nullable=false)
    private String name;
    
    @NotNull
    @Column(name="url", nullable=false)
    private String url;

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getUrl()
    {
        return url;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}
