package resource;

import dto.AgendaData;
import dto.BeritaData;
import entity.Berita;
import jakarta.inject.Inject;
import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repository.BeritaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("berita")
public class BeritaResource {
    @Inject
    private BeritaRepository beritaRepository;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<BeritaData> getBerita(){
        List<Berita>beritaList = beritaRepository.listAll();

        return beritaList.stream()
                .map(item -> new BeritaData(item.getId(),item.getJudulberita(), item.getTanggal(), item.getTanggalberita(),item.getImage(),item.getDeskripsi(),item.getKategori(),item.getCreated()))
                .collect(Collectors.toList());
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBerita(BeritaData beritaData){
        Berita berita = new Berita();
        berita.setJudulberita(beritaData.judulberita());
        berita.setTanggal(beritaData.tanggal());
        berita.setTanggalberita(beritaData.tanggalberita());
        berita.setImage(beritaData.image());
        berita.setDeskripsi(beritaData.deskripsi());
        berita.setKategori(beritaData.kategori());
        berita.setCreated(Instant.now());

        beritaRepository.persist(berita);

        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBerita(@PathParam("id")Long id,BeritaData beritaData){
        Optional<Berita>beritaOptional = beritaRepository.findByIdOptional(id);
        if(beritaOptional.isPresent()){
            Berita berita = beritaOptional.get();
            berita.setJudulberita(beritaData.judulberita());
            berita.setTanggal(beritaData.tanggal());
            berita.setImage(beritaData.image());
            berita.setDeskripsi(beritaData.deskripsi());
            berita.setKategori(beritaData.kategori());

            beritaRepository.persist(berita);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBerita(@PathParam("id")Long id){
        Optional<Berita>beritaOptional = beritaRepository.findByIdOptional(id);
        if(beritaOptional.isPresent()){
            beritaRepository.delete(beritaOptional.get());
            return Response.ok().build();
        }
            return  Response.status(Response.Status.NOT_FOUND).build();
    }


}
