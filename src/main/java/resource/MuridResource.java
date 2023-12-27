package resource;

import dto.MuridData;
import entity.Murid;
import jakarta.inject.Inject;
import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repository.MuridRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("murid")
public class MuridResource {
    @Inject
    private MuridRepository muridRepository;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<MuridData>getMurid(){
        List<Murid>muridList = muridRepository.listAll();

        return muridList.stream()
                .map(item -> new MuridData(item.getId(), item.getNama(),item.getNamawali(),item.getNo_telp(),item.getNis(),item.getTgl_lahir(),item.getUmur(),item.getAlamat(), item.getFoto()))
                .collect(Collectors.toList());

    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMurid(MuridData muridData){
        Murid murid = new Murid();
        murid.setNama(muridData.nama());
        murid.setNamawali(muridData.namawali());
        murid.setNo_telp(muridData.no_telp());
        murid.setNis(muridData.nis());
        murid.setTgl_lahir(muridData.tgl_lahir());
        murid.setAlamat(muridData.alamat());
        murid.setFoto(muridData.foto());

        muridRepository.persist(murid);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMurid(@PathParam("id")Long id,MuridData muridData){
        Optional<Murid>muridOptional = muridRepository.findByIdOptional(id);

        if(muridOptional.isPresent()){
            Murid murid = muridOptional.get();
            murid.setNama(muridData.nama());
            murid.setNamawali(muridData.namawali());
            murid.setNo_telp(muridData.no_telp());
            murid.setNis(muridData.nis());
            murid.setTgl_lahir(muridData.tgl_lahir());
            murid.setAlamat(muridData.alamat());
            murid.setFoto(muridData.foto());

            muridRepository.persist(murid);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMurid(@PathParam("id")Long id){
        Optional<Murid>muridOptional = muridRepository.findByIdOptional(id);

        if(muridOptional.isPresent()){
            muridRepository.delete(muridOptional.get());
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
