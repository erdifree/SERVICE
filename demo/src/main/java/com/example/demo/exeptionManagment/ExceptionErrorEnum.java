package com.example.demo.exeptionManagment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum ExceptionErrorEnum {
    CREDENZIALI_VALIDE(000, "Credenziali valide"),
    CREDENZIALI_NON_VALIDE(997, "Credenziali non valide"),
    ACCOMPAGNATORE_NON_AUTORIZZATO(970, "Accompagnatore non autorizzato"),
    CARATTERI_NON_AMMESSI_CF(900,"CARATTERE SPECIALE NON AMMESSI NEL CODICE FISCALE"),
    CF_NON_VALIDO(901,"CODICE FISCALE NON CORRETTO"),
    CF_NON_VALIDO_LUNGHEZZA(902,"Codice fiscale deve avere lunghezza 16 caratteri"),
    UTENTE_NON_REGISTRATO(999,"Utente non Registrato"),
    VALIDAZIONE_COORDINATE_ORIGINE(950,"Il   campo   [origine-coordinate]  non  è  valido.  Formato  ammesso: GG.DDDDDD,GG.DDDDDD"),
    VALIDAZIONE_COORDINATE_DESTINAZIONE(950,"Il   campo   [destinazione-coordinate]  non  è  valido.  Formato  ammesso: GG.DDDDDD,GG.DDDDDD"),
    TIPO_BIGLIETTO_NON_VALID(950,"Il valore del campo [tipo] non è valido. Valori ammessi: base | abbonamento"),
    DATA_INIZIO_VAL(950,"Il formato del campo [data-inizio-val] non è valido. Formato ammesso: DD/MM/YYYY"),
    DATA_Fine_VAL(950,"Il formato del campo [data-fine-val] non è valido. Formato ammesso: DD/MM/YYYY"),
    RICHIESTE_NON_EMESSE_DAL_UTENTE(950, "Non ci sono  richeste  emese da  questo utente");

    @Getter
    private final int code;
    @Getter
    private final String message;

    @Override
    public String toString() {
        return this.code + ": " + this.message;
    }
}
