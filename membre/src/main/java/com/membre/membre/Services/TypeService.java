package com.membre.membre.Services;



import com.membre.membre.Entities.Type;

import java.util.List;

public interface TypeService {
    public Type createType(Type type);
    public Type getType(Long typeId);
    public List<Type> getAllTypes();
    public Type updateType(Long typeId);
    public void delete(Long typeId);
}
