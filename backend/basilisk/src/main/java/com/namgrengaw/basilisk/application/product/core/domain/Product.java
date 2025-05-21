package com.namgrengaw.basilisk.application.product.core.domain;

import com.namgrengaw.basilisk.application.infrastructure.components.Description;
import com.namgrengaw.basilisk.application.infrastructure.components.Identifier;
import com.namgrengaw.basilisk.application.infrastructure.components.Name;
import com.namgrengaw.basilisk.application.infrastructure.components.Status;

import java.util.Objects;

import static com.namgrengaw.basilisk.application.infrastructure.validators.GuardUtil.required;

public class Product {

    private final Identifier id;
    private final Name name;
    private Description description;
    private final Status status;

    public Product(Name name, Status status) {
        required(name, "The name is required!");
        required(name, "The status is required!");

        this.id = new Identifier();
        this.name = name;
        this.status = status;
    }

    public Product(Identifier id, Name name, Status status) {
        required(name, "The id is required!");
        required(name, "The name is required!");
        required(name, "The status is required!");

        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Product(Identifier id, Name name, Description description, Status status) {
        this(id, name, status);

        required(name, "The description is required!");
        this.description = description;
    }

    public Product(Name name, Description description, Status status) {
        this(new Identifier(), name, description, status);
    }

    public void updateName(final String name) {
        this.name.update(name);
    }

    public void updateDescription(final String description) {
        if(Objects.isNull(this.description))
            this.description = new Description(description);
        else
            this.description.update(description);
    }

    public void changeStatus(final boolean status) {
        if(this.status.isActive() != status)
            this.status.setStatus(status);
    }

    public String getId() {
        return id.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public String getDescription() {
        return description.getValue();
    }

    public boolean getStatus() {
        return status.isActive();
    }
}