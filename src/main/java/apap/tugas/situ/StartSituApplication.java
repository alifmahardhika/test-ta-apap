//package apap.tugas.situ;
//
//import apap.tugas.situ.model.RoleModel;
//import apap.tugas.situ.model.UserModel;
//import apap.tugas.situ.service.RoleService;
//import apap.tugas.situ.service.UserService;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class StartSituApplication implements ApplicationRunner {
//
//    private RoleService roleService;
//    private UserService userService;
//
//    @Override
//    public void run(ApplicationArguments arges) throws  Exception{
//        //BUAT ROLEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
//        if (roleService.findRoleByName("Kepala Sekolah") == null){
//            RoleModel initialAdmin = new RoleModel();
//            initialAdmin.setNama("Kepala Sekolah");
//            initialAdmin.setId(Long.valueOf(1));
//            roleService.addRole(initialAdmin);
//        }
//
//        if (roleService.findRoleByName("Admin TU") == null){
//            RoleModel initialAdmin = new RoleModel();
//            initialAdmin.setNama("Admin TU");
//            initialAdmin.setId(Long.valueOf(2));
//            roleService.addRole(initialAdmin);
//        }
//        if (roleService.findRoleByName("Guru") == null){
//            RoleModel initialAdmin = new RoleModel();
//            initialAdmin.setNama("Guru");
//            initialAdmin.setId(Long.valueOf(3));
//            roleService.addRole(initialAdmin);
//        }
//
//        if (roleService.findRoleByName("Siswa") == null){
//            RoleModel initialAdmin = new RoleModel();
//            initialAdmin.setNama("Siswa");
//            initialAdmin.setId(Long.valueOf(4));
//            roleService.addRole(initialAdmin);
//        }
//
//        if (roleService.findRoleByName("Pustakawan") == null){
//            RoleModel initialAdmin = new RoleModel();
//            initialAdmin.setNama("Pustakawan");
//            initialAdmin.setId(Long.valueOf(5));
//            roleService.addRole(initialAdmin);
//        }
//
//        if (roleService.findRoleByName("Pengurus Koperasi") == null){
//            RoleModel initialAdmin = new RoleModel();
//            initialAdmin.setNama("Pengurus Koperasi");
//            initialAdmin.setId(Long.valueOf(6));
//            roleService.addRole(initialAdmin);
//        }
//
//        if (roleService.findRoleByName("Anggota Koperasi") == null){
//            RoleModel initialAdmin = new RoleModel();
//            initialAdmin.setNama("Anggota Koperasi");
//            initialAdmin.setId(Long.valueOf(7));
//            roleService.addRole(initialAdmin);
//        }
//
//        //BUAT USERRRRRRRRRRRRRRRRRRRRRRR
//        if (userService.getUser("Kepala Sekolah")==null){
//            UserModel user = new UserModel();
//            user.setUsername("Kepala Sekolah");
//            user.setPassword("Kepala Sekolah");
//            user.setRole(roleService.findRoleByName("Kepala Sekolah"));
//            userService.addUser(user);
//        }
//
//        if (userService.getUser("Admin TU")==null){
//            UserModel user = new UserModel();
//            user.setUsername("Admin TU");
//            user.setPassword("Admin TU");
//            user.setRole(roleService.findRoleByName("Admin TU"));
//            userService.addUser(user);
//        }
//
//        if (userService.getUser("Guru")==null){
//            UserModel user = new UserModel();
//            user.setUsername("Guru");
//            user.setPassword("Guru");
//            user.setRole(roleService.findRoleByName("Guru"));
//            userService.addUser(user);
//        }
//
//        if (userService.getUser("Siswa")==null){
//            UserModel user = new UserModel();
//            user.setUsername("Siswa");
//            user.setPassword("Siswa");
//            user.setRole(roleService.findRoleByName("Siswa"));
//            userService.addUser(user);
//        }
//
//        if (userService.getUser("Pustakawan")==null){
//            UserModel user = new UserModel();
//            user.setUsername("Pustakawan");
//            user.setPassword("Pustakawan");
//            user.setRole(roleService.findRoleByName("Pustakawan"));
//            userService.addUser(user);
//        }
//
//        if (userService.getUser("Pengurus Koperasi")==null){
//            UserModel user = new UserModel();
//            user.setUsername("Pengurus Koperasi");
//            user.setPassword("Pengurus Koperasi");
//            user.setRole(roleService.findRoleByName("Pengurus Koperasi"));
//            userService.addUser(user);
//        }
//
//        if (userService.getUser("Anggota Koperasi")==null){
//            UserModel user = new UserModel();
//            user.setUsername("Anggota Koperasi");
//            user.setPassword("Anggota Koperasi");
//            user.setRole(roleService.findRoleByName("Anggota Koperasi"));
//            userService.addUser(user);
//        }
//    }
//}
