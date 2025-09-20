# MusicApp - Ứng dụng nghe nhạc Android

## 📱 Giới thiệu

MusicApp là một ứng dụng nghe nhạc Android được phát triển bằng Kotlin, cung cấp trải nghiệm nghe nhạc trực tuyến với giao diện hiện đại và các tính năng phong phú.

## ✨ Tính năng chính

### 🎵 Phát nhạc
- Phát nhạc trực tuyến với chất lượng cao
- Điều khiển phát nhạc cơ bản (Play/Pause, Next/Previous)
- Chế độ phát ngẫu nhiên (Shuffle)
- Chế độ lặp lại (Repeat)
- Thanh tiến trình có thể điều chỉnh thời gian phát

### 🔍 Tìm kiếm
- Tìm kiếm bài hát theo tên
- Tìm kiếm real-time với API
- Hiển thị kết quả tìm kiếm trực quan

### 📚 Quản lý nội dung
- Quản lý playlist cá nhân
- Hiển thị danh sách album
- Phân loại theo chủ đề (Topic Category)
- Danh sách bài hát yêu thích

### 👤 Xác thực
- Đăng nhập bằng Google
- Quản lý tài khoản người dùng

## 🛠️ Công nghệ sử dụng

### Ngôn ngữ lập trình
- **Kotlin** - Ngôn ngữ chính
- **Java** - Hỗ trợ

### Framework & Library
- **Android SDK** (API 28-33)
- **Retrofit2** - Xử lý API calls
- **Gson** - Parse JSON data
- **Picasso/Glide** - Load và cache hình ảnh
- **Material Design** - UI/UX components

## 📁 Cấu trúc dự án

```
app/src/main/java/com/example/music/
├── activity/           # Các Activity chính
│   ├── MainActivity.kt
│   ├── LoginActivity.kt
│   ├── PlayMusicActivity.kt
│   └── ListSongsActivity.kt
├── fragment/           # Các Fragment
│   ├── HomeFragment.kt
│   ├── SearchFragment.kt
│   ├── PlaySongsFragment.kt
│   └── ...
├── adapter/            # RecyclerView Adapters
│   ├── SongAdapter.kt
│   ├── PlayListAdapter.kt
│   └── ...
├── model/              # Data Models
│   ├── Songs.kt
│   ├── PlayList.kt
│   └── ...
└── service/            # API Services
    ├── APIService.kt
    ├── DataService.kt
    └── APIRetrofitClient.kt
```

## 🚀 Cài đặt và chạy

### Yêu cầu hệ thống
- Android Studio Arctic Fox trở lên
- Android SDK API 28+
- Java 8+

## 📱 Giao diện

### Màn hình chính
- **Tab Trang chủ**: Hiển thị playlist, album, chủ đề nổi bật
- **Tab Tìm kiếm**: Tìm kiếm bài hát và nghệ sĩ

### Màn hình phát nhạc
- **Giao diện đĩa nhạc**: Hiển thị hình ảnh album xoay tròn
- **Danh sách bài hát**: Hiển thị playlist hiện tại
- **Điều khiển**: Play/Pause, Next/Previous, Shuffle, Repeat

## 🔧 Cấu hình API

Ứng dụng sử dụng REST API để lấy dữ liệu nhạc. Các endpoint chính:

- `GET /Service/select_songs.php` - Lấy danh sách bài hát
- `GET /Service/select_playlist.php` - Lấy danh sách playlist
- `GET /Service/select_album.php` - Lấy danh sách album
- `POST /Service/okoko.php` - Tìm kiếm bài hát

## 🎨 Tính năng UI/UX

- **Material Design** với giao diện hiện đại
- **Responsive design** cho nhiều kích thước màn hình
- **Smooth animations** cho trải nghiệm mượt mà
- **Dark/Light theme** support
- **Custom icons** và hình ảnh

## 📊 Hiệu suất

- **Lazy loading** cho danh sách lớn
- **Image caching** với Picasso/Glide
- **Memory management** tối ưu cho MediaPlayer
- **Background processing** cho tải dữ liệu

## 🤝 Đóng góp

Mọi đóng góp đều được chào đón! Hãy:

1. Fork project
2. Tạo feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Tạo Pull Request

## 👨‍💻 Tác giả

**CongTinh** - [GitHub](https://github.com/TinhSoMa)

## 📞 Liên hệ

- Email: congtinh06032003@gmail.com

