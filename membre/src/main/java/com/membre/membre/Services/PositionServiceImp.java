package com.membre.membre.Services;

import com.membre.membre.Entities.Position;
import com.membre.membre.Repositories.MemberRepository;
import com.membre.membre.Repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImp implements PositionService{
    @Autowired
    PositionRepository positionRepository;

    public List<Position> obtenirPositionStatusFalse() {
        return positionRepository.findByStatusFalse();
    }
}
