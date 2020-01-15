package instagram2.comments.api.v1.resources;

import instagram2.comments.lib.Comment;

import instagram2.comments.services.bean.CommentsBean;
import instagram2.comments.services.configuration.ConfigProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Path("comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentsResource {

    @Inject
    private CommentsBean commentsBean;

    @GET
    //@Counted
    public Response getComments(@QueryParam("imageId") Integer imageId) {

        List<Comment> comments;

        if (imageId != null) {
            comments = commentsBean.getCommentsForImage(imageId);
        } else {
            comments = commentsBean.getComments();
        }

        return Response.ok(comments).build();
    }

    @GET
    @Path("count")
    public Response getCommentsCount(@QueryParam("imageId") Integer imageId) {

        List<Comment> comments;

        if (imageId != null) {
            comments = commentsBean.getCommentsForImage(imageId);
        } else {
            comments = commentsBean.getComments();
        }

        return Response.ok(comments.size()).build();
    }

    @POST
    public Response createComment(Comment comment) {
        commentsBean.createComment(comment);
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/{userId}/{imageId}")
    public Response createComment(@PathParam("userId") String userId,
                                  @PathParam("imageId") Integer imageId,
                                  Comment comment) {
        comment.setImageId(imageId);
        comment.setAuthorId(userId);
        comment.setId(UUID.randomUUID().toString());
        commentsBean.createComment(comment);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{commentId}")
    public Response deleteComment(@PathParam("commentId") String commentId) {
        if (commentsBean.deleteComment(commentId)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
   /* @Inject
    private ConfigProperties confProp;

    @GET
    @Path("/{imageId}")
    public Response getComments(@PathParam("imageId") Integer imageId) {

        List<Comment> comments = new LinkedList<Comment>();

        try (
                Connection conn = DriverManager.getConnection(confProp.getDburl(), confProp.getDbuser(), confProp.getDbpass());
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM comments.comments WHERE \"imageid\" = "+"'"+imageId+"'");
        ) {
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getString(1));
                comment.setText(rs.getString(2));
                comments.add(comment);
            }
        }
        catch (SQLException e) {
            System.err.println(e);
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        return Response.ok(comments).build();

    }
    @DELETE
    @Path("/{commentId}")
    public Response deleteComment(@PathParam("commentId") int commentid) {
        try (
                Connection conn = DriverManager.getConnection(confProp.getDburl(), confProp.getDbuser(), confProp.getDbpass());
                Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate("DELETE FROM comments.comments WHERE \"id\" = " + "'"+commentid+"'");
        }
        catch (SQLException e) {
            System.err.println(e);
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        return Response.noContent().build();
    }

    @POST

    public Response addNewComment(@QueryParam("imageId") int imageId,
                                  Comment comment) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/comments", confProp.getDbuser(), confProp.getDbpass());
                Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate("INSERT INTO comments.comments (comment, imageid) VALUES ('"
                            + comment.getText() + "', '"+ imageId + "')",
                    Statement.RETURN_GENERATED_KEYS);
        }
        catch (SQLException e) {
            System.err.println(e);
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        return Response.status(Response.Status.CREATED).build();
    }*/


}
