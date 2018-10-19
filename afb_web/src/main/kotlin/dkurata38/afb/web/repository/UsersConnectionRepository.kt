//package dkurata38.afb.web.repository
//
//import org.springframework.social.connect.Connection
//import org.springframework.social.connect.ConnectionRepository
//import org.springframework.social.connect.UsersConnectionRepository
//
//class UsersConnectionRepository: UsersConnectionRepository {
//    /**
//     * Find the ids of the users who are connected to the specific provider user accounts.
//     * @param providerId the provider id, e.g. "facebook"
//     * @param providerUserIds the set of provider user ids e.g. ("125600", "131345", "54321").
//     * @return the set of user ids connected to those service provider users, or empty if none.
//     */
//    override fun findUserIdsConnectedTo(providerId: String?, providerUserIds: MutableSet<String>?): MutableSet<String> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    /**
//     * Find the ids for local application users that have the given [Connection].
//     * Used to support the ProviderSignIn scenario where the user id returned is used to sign a local application user in using his or her provider account.
//     * No entries indicates no application users are associated with the connection; ProviderSignInController will offer the user a signup page to register with the app.
//     * A single entry indicates that exactly one application user is associated with the connection and is used to sign in that user via ProviderSignInController.
//     * Multiple entries indicate that multiple application users are associated with the connection and handled as an error by ProviderSignInController.
//     * @param connection the service provider connection resulting from the provider sign-in attempt
//     * @return the user ids associated with the connection.
//     */
//    override fun findUserIdsWithConnection(connection: Connection<*>?): MutableList<String> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    /**
//     * Create a single-user [ConnectionRepository] instance for the user assigned the given id.
//     * All operations on the returned repository instance are relative to the user.
//     * @param userId the id of the local user account.
//     * @return the ConnectionRepository, exposing a number of operations for accessing and updating the given user's provider connections.
//     */
//    override fun createConnectionRepository(userId: String?): ConnectionRepository {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}