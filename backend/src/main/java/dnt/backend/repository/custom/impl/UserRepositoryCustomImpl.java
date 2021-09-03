package dnt.backend.repository.custom.impl;

import com.querydsl.jpa.impl.JPAQuery;
import dnt.backend.model.entity.FriendShip;
import dnt.backend.model.entity.QFriendShip;
import dnt.backend.model.entity.QUser;
import dnt.backend.model.entity.User;
import dnt.backend.repository.custom.UserRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    private final EntityManager em;

    public UserRepositoryCustomImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<String> getFriends(String me) {
        return new JPAQuery<FriendShip>(em)
                .from(QFriendShip.friendShip)
                .where(QFriendShip.friendShip.me.eq(me))
                .fetch()
                .stream()
                .map(relation -> relation.getYou())
                .collect(Collectors.toList());
    }
}
