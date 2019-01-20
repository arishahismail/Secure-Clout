package client.springbootvuejs.repository;

import java.util.List;


import client.springbootvuejs.domain.CloutFiles;

public interface FileRepository {
    CloutFiles saveFiles();

    List<CloutFiles> findByemail();

    void deleteFile(CloutFiles cloutfiles);

}