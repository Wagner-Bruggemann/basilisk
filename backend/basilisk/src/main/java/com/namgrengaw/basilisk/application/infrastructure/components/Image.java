package com.namgrengaw.basilisk.application.infrastructure.components;

import static com.namgrengaw.basilisk.application.infrastructure.validators.ObjectUtils.evaluateNonNullObjects;

public class Image {

    private final Identifier id;
    private final Name name;
    private final Path path;

    public Image(Identifier id, Name name, Path path) {
        evaluateNonNullObjects(id);
        evaluateNonNullObjects(name);
        evaluateNonNullObjects(path);

        this.id = id;
        this.name = name;
        this.path = path;
    }

    public void updateName(String name) {
        evaluateNonNullObjects(name);
        this.name.update(name);
    }

    public void updatePath(String path) {
        evaluateNonNullObjects(path);
        this.path.update(path);
    }

    public String getId() {
        return this.id.getValue();
    }

    public String getName() {
        return this.name.getValue();
    }

    public String getPath() {
        return this.path.getValue();
    }

}
