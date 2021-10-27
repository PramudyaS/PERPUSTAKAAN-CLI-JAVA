/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import controller.PerpustakaanJpaController;
import model.Perpustakaan;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tm7a {

    /**
     * @param args the command line arguments
     */
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    public static void main(String[] args) {
        // TODO code application logic here
        while (true) {
            showMenu();
        }

    }
     static void showMenu() {
        System.out.println("\n========= MENU UTAMA =========");
        System.out.println("1. Insert Data");
        System.out.println("2. Show Data");
        System.out.println("3. Edit Data");
        System.out.println("4. Delete Data");
        System.out.println("0. Keluar");
        System.out.println("");
        System.out.print("PILIHAN> ");

        try {
            int pilihan = Integer.parseInt(input.readLine());

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    insertBuku();
                    break;
                case 2:
                    showData();
                    break;
                case 3:
                    updateBuku();
                    break;
                case 4:
                    deleteData();
                    break;
                default:
                    System.out.println("Pilihan salah!");
            }
        } catch (NumberFormatException | IOException e) {
        }
    }

    static void insertBuku() {
        try {
            Perpustakaan perpus = new Perpustakaan();

            System.out.print("Judul: ");
            String judul = input.readLine().trim();

            System.out.print("Pengarang: ");
            String pengarang = input.readLine().trim();

            System.out.print("ISBN: ");
            String isbn = input.readLine().trim();

            System.out.print("Tahun Terbit: ");
            String tahun_terbit = input.readLine().trim();

            perpus.setJudul(judul);
            perpus.setPengarang(pengarang);
            perpus.setIsbn(isbn);
            perpus.setTahun_terbit(tahun_terbit);

            EntityManagerFactory objFactory = Persistence.createEntityManagerFactory("Tm7aPU");

            PerpustakaanJpaController jpa = new PerpustakaanJpaController(objFactory);

            
            jpa.create(perpus);
            
        } catch (IOException e) {
            
        }
        }

        static void updateBuku()  {
        try {
            Perpustakaan perpus = new Perpustakaan();

            System.out.print("Id Buku: ");
            int id_buku = Integer.parseInt(input.readLine().trim());

            System.out.print("Judul: ");
            String judul = input.readLine().trim();

            System.out.print("Pengarang: ");
            String pengarang = input.readLine().trim();

            System.out.print("ISBN: ");
            String isbn = input.readLine().trim();

            System.out.print("Tahun Terbit: ");
            String tahun_terbit = input.readLine().trim();
            
            perpus.setIdBuku(id_buku);
            perpus.setJudul(judul);
            perpus.setPengarang(pengarang);
            perpus.setIsbn(isbn);
            perpus.setTahun_terbit(tahun_terbit);

            EntityManagerFactory objFactory = Persistence.createEntityManagerFactory("Tm7aPU");

            PerpustakaanJpaController jpa = new PerpustakaanJpaController(objFactory);

             try {
                jpa.edit(perpus);
            } catch (Exception ex) {
                Logger.getLogger(Tm7a.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            } catch (IOException e) {
            
            }
        }

        public static void deleteData()
        {
            try {
                System.out.print("Id Buku: ");
                int id_buku = Integer.parseInt(input.readLine().trim());

                EntityManagerFactory objFactory = Persistence.createEntityManagerFactory("Tm7aPU");

                PerpustakaanJpaController jpa = new PerpustakaanJpaController(objFactory);
                jpa.destroy(id_buku);
            }catch (Exception ex){
                Logger.getLogger(Tm7a.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void showData()
        {
            try {
                EntityManagerFactory objFactory = Persistence.createEntityManagerFactory("Tm7aPU");

                PerpustakaanJpaController jpa = new PerpustakaanJpaController(objFactory);
                List<Perpustakaan> perpustakaanList = jpa.findPerpustakaanEntities();

                System.out.format("Semua Data%n");
                System.out.format("+-----------------+------+%n");

                for(int i = 0; i < perpustakaanList.size();i++ )
                {
                    System.out.println("ID Buku :" + perpustakaanList.get(i).getIdBuku());
                    System.out.println("Judul :" + perpustakaanList.get(i).getJudul());
                    System.out.println("Pengarang :" + perpustakaanList.get(i).getPengarang());
                    System.out.println("ISBN :" + perpustakaanList.get(i).getIsbn());
                    System.out.println("Tahun Terbit :" + perpustakaanList.get(i).getTahun_terbit());
                    System.out.format("+-----------------+------+%n");
                }
            }catch (Exception ex)
            {
                Logger.getLogger(Tm7a.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
