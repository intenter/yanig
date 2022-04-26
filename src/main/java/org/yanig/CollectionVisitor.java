package org.yanig;

import java.io.IOException;

public interface CollectionVisitor {
    void preFolder(Folder folder) throws IOException;
    void postFolder(Folder folder);
    void image(ImageFile imageFile) throws IOException;
}
