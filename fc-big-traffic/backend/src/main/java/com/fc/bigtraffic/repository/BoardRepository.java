package com.fc.bigtraffic.repository;

import com.fc.bigtraffic.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>  {

}
