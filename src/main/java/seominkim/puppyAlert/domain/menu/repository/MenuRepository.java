package seominkim.puppyAlert.domain.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seominkim.puppyAlert.domain.menu.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, String> {
}
