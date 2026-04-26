package mk.ukim.finki.emclab.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "shopping_carts")
@NamedEntityGraph(
        name = "shopping-cart-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "cartItems", subgraph = "cart-item-subgraph"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "cart-item-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("book")
                        }
                )
        }
)
public class ShoppingCart extends BaseAuditableEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToMany(mappedBy = "shoppingCart")
    private List<CartItem> cartItems = new ArrayList<>();

    public ShoppingCart(User user) {
        this.user = user;
    }
}

