package com.example.demo.Service;


import com.example.demo.DTO.BodyResponse;
import com.example.demo.DTO.CredenzialiRequest;
import com.example.demo.Entiy.DatiTitolare;
import com.example.demo.Entiy.ElencoCodici;
import com.example.demo.Entiy.Esito;
import com.example.demo.Repository.DatiUtentiRepositopry;
import com.example.demo.exeptionManagment.ApiRequestExeption;
import com.example.demo.exeptionManagment.ExceptionErrorEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CredenzialiService {

    private final DatiUtentiRepositopry datiUtentiRepositopry;

    /*Metodo publico che verifica le credenziali */
    public BodyResponse verificaCredenziale(CredenzialiRequest credenzialiRequest) {
        if (credenzialiRequest.getCfAccompagnatore() == null) {
            return verificaCredenzialeSenzaAccompagnatore(credenzialiRequest);
        } else {
            return verificaCredenzialeConAccompagnatore(credenzialiRequest);
        }
    }


    /*Metodo privato che verifica le credenziali csenza accompagnatore inserito */
    private BodyResponse verificaCredenzialeSenzaAccompagnatore(CredenzialiRequest credenzialiRequest) {
        if (CodiceFiscaleService.validate(credenzialiRequest.getCfTitolare()) == true){
        Optional<DatiTitolare> responseTitolare = datiUtentiRepositopry.findByCf(credenzialiRequest.getCfTitolare());
        if (responseTitolare.isEmpty()) {
            throw new ApiRequestExeption(ExceptionErrorEnum.UTENTE_NON_REGISTRATO.getMessage());
        } else {
            if (verificaTipoBiglietto(credenzialiRequest.getTipoDiBiglietto().toString()) == true) {
                String esito = "ok";
                ElencoCodici elencoCodici = ElencoCodici
                        .builder()
                        .messaggio(ExceptionErrorEnum.CREDENZIALI_VALIDE.getMessage())
                        .codice(String.valueOf(ExceptionErrorEnum.CREDENZIALI_VALIDE.getCode()))
                        .build();
                BodyResponse bodyResponse = new BodyResponse(
                        responseTitolare.get().getNome(),
                        responseTitolare.get().getDataNascita()
                        , esito,
                        responseTitolare.get().getCognome(),
                        elencoCodici);
                return bodyResponse;
            }else{
                throw new ApiRequestExeption(ExceptionErrorEnum.TIPO_BIGLIETTO_NON_VALID.getMessage());
            }
        }
        }else {
            throw new ApiRequestExeption(ExceptionErrorEnum.CF_NON_VALIDO.getMessage());
        }
    }

    /*Metodo privato che verifica le credenziali con accompagnatore inserito */
    private BodyResponse verificaCredenzialeConAccompagnatore(CredenzialiRequest credenzialiRequest) {
        if (CodiceFiscaleService.validate(credenzialiRequest.getCfTitolare()) == true) {
            Optional<DatiTitolare> responseTitolare = datiUtentiRepositopry.findByCf(credenzialiRequest.getCfTitolare());

            if (responseTitolare.isEmpty()) {
                throw new ApiRequestExeption(ExceptionErrorEnum.UTENTE_NON_REGISTRATO.getMessage());
            } else {
                /*In questa condizione verifico se  il CF del accompagnatore  è corretto*/
                if (CodiceFiscaleService.validate(credenzialiRequest.getCfAccompagnatore()) == true) {
                    /*In questa condizione  verifico  se  con il CF del titolare  è assocciato  il cf dell'accompagnatore */
                    if (vericaAutorizzazioneAccompagnatore(responseTitolare.get().getDatiAccompagnatore().getCf(), credenzialiRequest.getCfAccompagnatore()) == true) {
                        /*In questa condizione  verifico   il biglietto se è base o abonamento  */
                        if (verificaTipoBiglietto(credenzialiRequest.getTipoDiBiglietto().toString()) == true) {
                            /*In questa condizione  verifico  le coordinate origine se hanno il patern gg.dddddd,gg.dddddd*/
                            if (verificaCoordinate(credenzialiRequest.getOrigineCoordinate()) == true && verificaCoordinate(credenzialiRequest.getDestinazioneCoordinate()) == true) {
                                BodyResponse bodyResponse = BodyResponse
                                        .builder()
                                        .nomeTitolare(responseTitolare.get().getNome())
                                        .dataNascitaTitolare(responseTitolare.get().getDataNascita())
                                        .esito("ok")
                                        .cognomeTitolare(responseTitolare.get().getCognome())
                                        .nomeAccompagnatore(responseTitolare.get().getDatiAccompagnatore().getNome())
                                        .dataNascitaAccompagnatore(responseTitolare.get().getDatiAccompagnatore().getDataNascita())
                                        .cognomeAccompagnatore(responseTitolare.get().getDatiAccompagnatore().getCognome())
                                        .elencoCodici(ElencoCodici
                                                .builder()
                                                .messaggio(ExceptionErrorEnum.CREDENZIALI_VALIDE.getMessage())
                                                .codice(String.valueOf(ExceptionErrorEnum.CREDENZIALI_VALIDE.getCode()))
                                                .build())
                                        .build();
                                return bodyResponse;
                            } else{
                                BodyResponse response;
                                if (!verificaCoordinate(credenzialiRequest.getOrigineCoordinate()) == true) {
                                    ObjectMapper objectMapper = new ObjectMapper();
                                    objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
                                    response = BodyResponse
                                        .builder()
                                            .esito(Esito.KO.name())
                                        .elencoCodici(ElencoCodici
                                                .builder()
                                                .codice(String.valueOf(ExceptionErrorEnum.VALIDAZIONE_COORDINATE_ORIGINE.getCode()))
                                                .messaggio(ExceptionErrorEnum.VALIDAZIONE_COORDINATE_ORIGINE.getMessage())
                                                .build())
                                        .build();
                                return response;
                            } else {
                            response = BodyResponse
                                        .builder()
                                    .esito(Esito.KO.name())
                                        .elencoCodici(ElencoCodici
                                                .builder()
                                                .codice(String.valueOf(ExceptionErrorEnum.VALIDAZIONE_COORDINATE_DESTINAZIONE.getCode()))
                                                .messaggio(ExceptionErrorEnum.VALIDAZIONE_COORDINATE_DESTINAZIONE.getMessage())
                                                .build())
                                        .build();
                                return response;
                            }
                        }
                        } else{
                            throw new ApiRequestExeption(ExceptionErrorEnum.TIPO_BIGLIETTO_NON_VALID.getMessage());
                        }
                    } else{
                        throw new ApiRequestExeption(ExceptionErrorEnum.ACCOMPAGNATORE_NON_AUTORIZZATO.getMessage());
                    }
                } else {
                    BodyResponse response= BodyResponse
                            .builder()
                            .elencoCodici(ElencoCodici
                                    .builder()
                                    .codice(String.valueOf(ExceptionErrorEnum.ACCOMPAGNATORE_NON_AUTORIZZATO.getCode()))
                                    .messaggio(ExceptionErrorEnum.ACCOMPAGNATORE_NON_AUTORIZZATO.getMessage())
                                    .build())
                            .build();
                    return response;
                }
            }
        } else {
            BodyResponse response= BodyResponse
                    .builder()
                    .elencoCodici(ElencoCodici
                            .builder()
                            .codice(String.valueOf(ExceptionErrorEnum.CREDENZIALI_NON_VALIDE.getCode()))
                            .messaggio(ExceptionErrorEnum.CREDENZIALI_NON_VALIDE.getMessage())
                            .build())
                    .build();
            return response;
        }
    }

    /*metodo che confronta se le 2 stringhe sono uguali*/
    private boolean vericaAutorizzazioneAccompagnatore(String a, String b) {
        boolean flag = false;
        if (a.equals(b)) {
            return flag = true;
        } else {
            throw new ApiRequestExeption(ExceptionErrorEnum.ACCOMPAGNATORE_NON_AUTORIZZATO.getMessage());
        }
    }

    public static boolean verificaTipoBiglietto(String str) {
        if (str.equals("base") || str.equals("abbonamento")) {
            return true;
        }
        return false;
    }

    public static boolean verificaCoordinate(String str){
        if (str.matches("\\d{2}.\\d{6},\\d{2}.\\d{6}")) {return true;
        }else{
            return false;
        }
    }
    public static boolean verificaFormatoDataGGMMYYYY(String str){
        if (str.matches("(0[1-9]|[1-2][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}")) {
            return true;
        }else{
            return false;
        }
    }
}





