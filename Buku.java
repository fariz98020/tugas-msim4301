/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugas_2_msim4301;

/**
 *
 * @author ghozi
 */
public class Buku {
    String id;
    String judul;
    String penulis;
    String kategori;
    boolean tersedia;

    public Buku(String id, String judul, String penulis, String kategori, boolean tersedia) {
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.kategori = kategori;
        this.tersedia = tersedia;
    }
}