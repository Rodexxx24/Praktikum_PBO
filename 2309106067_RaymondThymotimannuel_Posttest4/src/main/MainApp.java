package main;

import models.*;
import services.KonsolService;
import utils.InputHelper;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainApp {
    public static void main(String[] args) {
        KonsolService konsolService = new KonsolService();
        ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();

        while (true) {
            System.out.println("\nSelamat datang di RajaKonsol");
            System.out.println("=== Silahkan Pilih Menu ===");
            System.out.println("1. Tambah Konsol");
            System.out.println("2. Lihat Konsol");
            System.out.println("3. Hapus Konsol");
            System.out.println("4. Tambah Transaksi");
            System.out.println("5. Lihat Transaksi");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            
            int pilihan = InputHelper.nextInt();
            InputHelper.nextLine();

            switch (pilihan) {
                case 1 -> {
                    System.out.print("Nama Konsol: ");
                    String nama = InputHelper.nextLine();
                    System.out.print("Harga Sewa: ");
                    double harga = InputHelper.nextDouble();
                    konsolService.tambahKonsol(new Konsol(konsolService.getSize() + 1, nama, harga));
                }
                case 2 -> konsolService.tampilkanKonsol();
                case 3 -> {
                    System.out.print("ID Konsol yang dihapus: ");
                    int id = InputHelper.nextInt();
                    konsolService.hapusKonsol(id);
                }
                case 4 -> {
                    if (konsolService.getSize() == 0) {
                        System.out.println("Belum ada konsol yang tersedia.");
                        break;
                    }

                    System.out.println("=== Pilih Konsol ===");
                    konsolService.tampilkanKonsol();
                    System.out.print("ID Konsol: ");
                    int idKonsol = InputHelper.nextInt();
                    Konsol konsol = konsolService.cariKonsolById(idKonsol);
                    if (konsol == null) {
                        System.out.println("Konsol tidak ditemukan.");
                        break;
                    }

                    System.out.print("Nama Pengguna: ");
                    String namaPengguna = InputHelper.nextLine();
                    Pengguna pengguna = new Pengguna(namaPengguna);

                    System.out.print("Tanggal Sewa (yyyy-mm-dd): ");
                    LocalDate tglSewa = LocalDate.parse(InputHelper.nextLine());

                    System.out.print("Tanggal Kembali (yyyy-mm-dd): ");
                    LocalDate tglKembali = LocalDate.parse(InputHelper.nextLine());

                    System.out.print("Jenis Transaksi (1 = Sewa, 2 = Pembelian): ");
                    int jenis = InputHelper.nextInt();

                    if (jenis == 1) {
                        System.out.print("Biaya Sewa: ");
                        double biayaSewa = InputHelper.nextDouble();
                        TransaksiSewa t = new TransaksiSewa(daftarTransaksi.size() + 1, pengguna, konsol, tglSewa, tglKembali, biayaSewa);
                        daftarTransaksi.add(t);
                        System.out.println("Total biaya (tanpa pajak): " + t.hitungBiaya());
                        System.out.println("Total biaya (+ pajak 10%): " + t.hitungBiaya(biayaSewa * 0.10));
                    } else if (jenis == 2) {
                        System.out.print("Harga Beli: ");
                        double hargaBeli = InputHelper.nextDouble();
                        TransaksiPembelian t = new TransaksiPembelian(daftarTransaksi.size() + 1, pengguna, konsol, tglSewa, tglKembali, hargaBeli);
                        daftarTransaksi.add(t);
                        System.out.println("Total harga (tanpa pajak): " + t.hitungBiaya());
                        System.out.println("Total harga (+ pajak 10%): " + t.hitungBiaya(hargaBeli * 0.10));
                    } else {
                        System.out.println("Jenis transaksi tidak valid.");
                    }
                }
                case 5 -> {
                    if (daftarTransaksi.isEmpty()) {
                        System.out.println("Belum ada transaksi.");
                    } else {
                        System.out.println("=== Daftar Transaksi ===");
                        for (Transaksi t : daftarTransaksi) {
                            System.out.println(t.toString());
                            System.out.println("Biaya (tanpa pajak): " + t.hitungBiaya());
                            System.out.println("Biaya (+ pajak 10%): " + t.hitungBiaya(t.hitungBiaya() * 0.10));
                            System.out.println("-----------------------------------");
                        }
                    }
                }
                case 6 -> {
                    System.out.println("Terima kasih telah menggunakan RajaKonsol!");
                    return;
                }
                default -> System.out.println("Menu tidak valid.");
            }
        }
    }
}
