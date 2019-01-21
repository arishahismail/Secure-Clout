package client.springbootvuejs.repository;

import java.util.List;

import client.springbootvuejs.domain.CloutFiles;

public interface FileRepository {
    CloutFiles saveFiles(CloutFiles cloutfiles);

    List<CloutFiles> findByemail();

    CloutFiles findByfileName(String file_name);

    CloutFiles updateFiles(CloutFiles cloutfiles);

    void deleteFile(CloutFiles cloutfiles);

}