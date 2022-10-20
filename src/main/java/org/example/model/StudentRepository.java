package org.example.model;

import org.frost.util.annotations.Repository;
import org.frost.util.datainterfaces.CRUDRepository;
@Repository
public interface StudentRepository extends CRUDRepository<Student> {
}
