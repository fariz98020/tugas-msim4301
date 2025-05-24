/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tugas_2_msim4301;

import java.util.Scanner;

/**
 *
 * @author ghozi
 */
public class Tugas_2_msim4301 {

    /**
     * @param args the command line arguments
     */
    static Buku[] daftarBuku = new Buku[20];
    static int jumlahBuku = 0;

    public static void main(String[] args) {
        inisialisasiBuku();
        menuUtama();
    }

    static void inisialisasiBuku() {
        daftarBuku[0] = new Buku("B001", "Laskar Pelangi", "Andrea Hirata", "Fiksi", true);
        daftarBuku[1] = new Buku("B002", "Sapiens", "Yuval Noah Harari", "Sejarah", true);
        daftarBuku[2] = new Buku("B003", "Algoritma Pemrograman", "S. Budi", "Teknologi", true);
        daftarBuku[3] = new Buku("B004", "Atomic Habits", "James Clear", "Non-Fiksi", true);
        jumlahBuku = 4;
    }

    static void menuUtama() {
        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== MENU UTAMA PERPUSTAKAAN ===");
            System.out.println("1. Tampilkan Daftar Buku");
            System.out.println("2. Peminjaman Buku");
            System.out.println("3. Pengembalian Buku");
            System.out.println("4. Manajemen Data Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine(); // konsumsi newline

            switch (pilihan) {
                case 1 -> tampilkanDaftarBuku();
                case 2 -> prosesPeminjaman();
                case 3 -> prosesPengembalian();
                case 4 -> manajemenBuku();
                case 5 -> System.out.println("Terima kasih telah menggunakan sistem perpustakaan.");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    static void tampilkanDaftarBuku() {
        System.out.println("\nDaftar Buku:");
        for (int i = 0; i < jumlahBuku; i++) {
            Buku b = daftarBuku[i];
            System.out.printf("- [%s] [%s] %s oleh %s (%s)\n",
                    b.tersedia ? "Tersedia" : "Dipinjam", b.id, b.judul, b.penulis, b.kategori);
        }
    }

    static void prosesPeminjaman() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nPeminjaman Buku (kosongkan untuk mengakhiri):");
        String[] idDipinjam = new String[10];
        int totalDipinjam = 0;

        while (true) {
            System.out.print("ID buku: ");
            String id = input.nextLine();

            if (id.equals("")) {
                if (totalDipinjam > 0) {
                    cetakStrukPeminjaman(idDipinjam, totalDipinjam);
                }
                break;
            }

            boolean ditemukan = false;
            for (int i = 0; i < jumlahBuku; i++) {
                Buku b = daftarBuku[i];
                if (b.id.equalsIgnoreCase(id)) {
                    ditemukan = true;
                    if (b.tersedia) {
                        b.tersedia = false;
                        idDipinjam[totalDipinjam++] = b.id;
                        System.out.println("Buku berhasil dipinjam.");
                    } else {
                        System.out.println("Buku sedang dipinjam. Pilih buku lain.");
                    }
                    break;
                }
            }
            if (!ditemukan) {
                System.out.println("ID buku tidak ditemukan. Ulangi input.");
            }
        }
    }

    static void prosesPengembalian() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nID buku yang dikembalikan: ");
        String id = input.nextLine();

        boolean ditemukan = false;
        for (int i = 0; i < jumlahBuku; i++) {
            Buku b = daftarBuku[i];
            if (b.id.equalsIgnoreCase(id)) {
                ditemukan = true;
                if (!b.tersedia) {
                    b.tersedia = true;

                    System.out.print("Hari keterlambatan (0 jika tepat waktu): ");
                    int terlambat = input.nextInt();
                    cetakStrukPengembalian(b, terlambat);
                } else {
                    System.out.println("Buku sudah dalam status tersedia.");
                }
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("ID buku tidak ditemukan di daftar.");
        }
    }

    static void manajemenBuku() {
        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== Manajemen Data Buku ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Ubah Buku");
            System.out.println("3. Hapus Buku");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1 -> tambahBuku();
                case 2 -> ubahBuku();
                case 3 -> hapusBuku();
                case 4 -> {
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 4);
    }

    static void tambahBuku() {
        Scanner input = new Scanner(System.in);
        System.out.println("Tambah Buku Baru:");
        System.out.print("ID Buku: ");
        String id = input.nextLine();
        System.out.print("Judul: ");
        String judul = input.nextLine();
        System.out.print("Penulis: ");
        String penulis = input.nextLine();
        System.out.print("Kategori: ");
        String kategori = input.nextLine();

        daftarBuku[jumlahBuku] = new Buku(id, judul, penulis, kategori, true);
        jumlahBuku++;
        System.out.println("Buku berhasil ditambahkan.");
    }

    static void ubahBuku() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan ID buku yang ingin diubah: ");
        String id = input.nextLine();

        for (int i = 0; i < jumlahBuku; i++) {
            if (daftarBuku[i].id.equalsIgnoreCase(id)) {
                Buku b = daftarBuku[i];
                 System.out.printf("- [%s] [%s] %s oleh %s (%s)\n",
                    b.tersedia ? "Tersedia" : "Dipinjam", b.id, b.judul, b.penulis, b.kategori);
                System.out.println("Data ditemukan. Masukkan data baru:");
                System.out.print("Judul: ");
                daftarBuku[i].judul = input.nextLine();
                System.out.print("Penulis: ");
                daftarBuku[i].penulis = input.nextLine();
                System.out.print("Kategori: ");
                daftarBuku[i].kategori = input.nextLine();
                System.out.print("Status (tersedia/dipinjam): ");
                String status = input.nextLine();
                daftarBuku[i].tersedia = status.equalsIgnoreCase("tersedia");
                System.out.println("Data buku berhasil diubah.");
                return;
            }
        }
        System.out.println("ID buku tidak ditemukan.");
    }

    static void hapusBuku() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan ID buku yang ingin dihapus: ");
        String id = input.nextLine();

        for (int i = 0; i < jumlahBuku; i++) {
            if (daftarBuku[i].id.equalsIgnoreCase(id)) {
                System.out.print("Yakin ingin menghapus? (y/n): ");
                String konfirmasi = input.nextLine();
                if (konfirmasi.equalsIgnoreCase("y")) {
                    for (int j = i; j < jumlahBuku - 1; j++) {
                        daftarBuku[j] = daftarBuku[j + 1];
                    }
                    jumlahBuku--;
                    System.out.println("Buku berhasil dihapus.");
                } else {
                    System.out.println("Penghapusan dibatalkan.");
                }
                return;
            }
        }
        System.out.println("ID buku tidak ditemukan.");
    }

    static void cetakStrukPeminjaman(String[] idDipinjam, int totalDipinjam) {
        System.out.println("\n===== STRUK PEMINJAMAN =====");
        for (int i = 0; i < totalDipinjam; i++) {
            for (int j = 0; j < jumlahBuku; j++) {
                if (daftarBuku[j].id.equalsIgnoreCase(idDipinjam[i])) {
                    Buku b = daftarBuku[j];
                    System.out.printf("- [%s] [%s] %s oleh %s (%s)\n",
                    b.tersedia ? "Tersedia" : "Dipinjam", b.id, b.judul, b.penulis, b.kategori);
                }
            }
        }
    }

    static void cetakStrukPengembalian(Buku b, int terlambat) {
        System.out.println("\n===== STRUK PENGEMBALIAN =====");
        System.out.printf("- [%s] [%s] %s oleh %s (%s)\n", "Dikembalikan", b.id, b.judul, b.penulis, b.kategori);
        System.out.println("Status: Sudah dikembalikan");

        if (terlambat > 7) {
            int denda = (terlambat - 7) * 5000;
            System.out.println("Hari keterlambatan: " + terlambat + " hari");
            System.out.println("Denda: Rp " + denda);
        } else {
            System.out.println("Tidak ada denda.");
        }
    }
}
