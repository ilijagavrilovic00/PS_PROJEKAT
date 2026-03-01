# Predlog promena projekta za usklađivanje sa sekvencnim dijagramima za račun

Ovaj dokument mapira zahteve iz dijagrama DS1/DS2/DS3 na trenutno stanje projekta i predlaže konkretne, inkrementalne izmene.

## 1) Šta već postoji u projektu

- Operacije za račun već postoje: dodavanje, ažuriranje, učitavanje računa i stavki računa.
- U klijentskoj formi za prikaz računa već postoje poruke uspeha/neuspeha za scenarije „nađi račune“, „nađi račun“, „zapamti račun“.
- U server niti postoji obrada svih ključnih operacija za račun (`DODAJ_RACUN`, `AZURIRAJ_RACUN`, `UCITAJ_RACUNE`, `UCITAJ_ZAPOSLENE`, `UCITAJ_IGRE`).

## 2) Glavni jaz u odnosu na dijagrame

Najveći jaz je **pretraga računa po kriterijumu** (račun/zaposleni/klijent/društvena igra):

- Trenutna operacija `UCITAJ_RACUNE` vraća sve račune bez kriterijuma.
- U formi za prikaz računa ne postoje filter polja (npr. combo za zaposlenog/klijenta/igru, broj računa, datum).

## 3) Predlog minimalnih izmena (bez lomljenja postojeće logike)

1. Uvesti novu sistemsku operaciju za pretragu:
   - `PRETRAZI_RACUNE`
   - Parametar: DTO kriterijuma (npr. `KriterijumRacuna`) sa opcionim poljima:
     - `idRacun`
     - `idZaposleni`
     - `idKlijent`
     - `idDrustvenaIgra`
     - `datumOd`, `datumDo` (opciono)

2. Na serveru dodati novu SO klasu:
   - `PretraziRacuneSO`
   - dinamički gradi `WHERE` uslov na osnovu ne-null kriterijuma.

3. U klijentu (forma za prikaz računa):
   - dodati panel „Kriterijumi pretrage“
   - dugme „Pretraži“
   - pri kliku slati `KriterijumRacuna` ka serveru i puniti tabelu rezultatima.

4. Poruke uskladiti sa dijagramima:
   - uspeh: „Sistem je našao račune po zadatim kriterijumima“
   - neuspeh: „Sistem ne može da nađe račune po zadatim kriterijumima“

## 4) Predlog „idealnog“ usklađivanja sa DS1/DS2/DS3

Ako želiš potpuno usklađivanje sa opisanim signalima:

- DS1 (Kreiraj račun)
  - `KreirajRacun(Racun)` → može biti mapirano kao lokalno kreiranje praznog modela računa na formi.
  - `PromeniRacun(Racun)` → postojeći `AZURIRAJ_RACUN`.
  - `vratiListuSviZaposleni`, `vratiListuSviKlijent`, `vratiListuSviDrustvenaIgra` → već postoje kroz učitavanje šifarnika.

- DS2 (Pretraži račun)
  - `PretraziRacun(Racun)` / `vratiListuRacun(kriterijum, List<Racun>)` → najbolje kao jedinstvena operacija `PRETRAZI_RACUNE` + `KriterijumRacuna` DTO.

- DS3 (Promeni račun)
  - kombinacija DS2 + `AZURIRAJ_RACUN` (već funkcionalno prisutno).

## 5) Redosled implementacije (preporuka)

1. Dodaj `KriterijumRacuna` u zajednički modul.
2. Dodaj `PRETRAZI_RACUNE` u `Operacija` enum i end-to-end komunikaciju.
3. Implementiraj `PretraziRacuneSO` i `Controller.pretraziRacune(...)`.
4. Dodaj UI filtere + dugme „Pretraži“ u `PrikazRacunaForma`.
5. Refaktoriši postojeće `UCITAJ_RACUNE` da ostane „default list all“.
6. Tek onda proširi validacije i poruke za sve alternativne scenarije.

## 6) Napomena o imenovanju

U dijagramima je mešano „signal“ i nazivi koji više liče na use-case operacije. U kodu je bolje zadržati:

- glagol + domen (`pretraziRacune`, `azurirajRacun`, `ucitajZaposlene`)
- jedan ulazni DTO za kriterijum umesto više preopterećenih signala.

To će dati čistiji API i lakše testiranje.
