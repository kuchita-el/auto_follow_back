package dkurata38.afb.domain.automateuser

import javax.persistence.*

@Entity
@Table(name = "automate_user")
class AutomateUser() {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_automate_user_id")
    @SequenceGenerator(name = "seq_automate_user_id", sequenceName = "seq_automate_user_id")
    private var id: Int? = null

    @Column(name = "user_id")
    private var userId: Int? = null

    constructor(userId: Int): this(){
        this.userId = userId
    }

    fun getId(): Int? = id
    fun getUserId(): Int? = userId
}