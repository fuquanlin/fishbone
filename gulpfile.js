var gulp = require('gulp');
var gutil = require('gulp-util');
var bower = require('bower');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var minifycss = require('gulp-minify-css');
var rename = require('gulp-rename');
var clean = require('gulp-clean');
var imagemin = require('gulp-imagemin');
var cache = require('gulp-cache');
var templateCache = require('gulp-angular-templatecache');
var ngAnnotate = require('gulp-ng-annotate');


var buildConfig = {
    projectName: 'fishbone',
    rootPath: 'dist',
    js_all: [
        "src/main/resources/static/common/config.js",
        "src/main/resources/static/common/common.js",
        "src/main/resources/static/common/service/api.service.js",
        "src/main/resources/static/common/service/common.service.js",
        "src/main/resources/static/common/service/user.service.js",
        "src/main/resources/static/common/service/role.service.js",
        "src/main/resources/static/common/service/log.service.js",
        "src/main/resources/static/index.js",
        "src/main/resources/static/templates/main.js",
        "src/main/resources/static/templates/welcome/welcome.js",
        "src/main/resources/static/templates/user/user.js",
        "src/main/resources/static/templates/role/role.js",
        "src/main/resources/static/templates/log/log.js"
    ],
    template_all: [
        "src/main/resources/static/templates/*.html",
        "src/main/resources/static/templates/*/*.html"
    ],
    js_lib: [
        "src/main/resources/static/lib/angular/angular.js",
        "src/main/resources/static/lib/angular-animate/angular-animate.js",
        "src/main/resources/static/lib/angular-ui-router/release/angular-ui-router.js",
        "src/main/resources/static/lib/angular-bootstrap/ui-bootstrap-tpls.js",
        "src/main/resources/static/lib/ng-file-upload/ng-file-upload-all.min.js",
        "src/main/resources/static/lib/jquery/dist/jquery.js",
        "src/main/resources/static/lib/bootstrap/dist/js/bootstrap.min.js",
        "src/main/resources/static/lib/metisMenu/dist/metisMenu.min.js",
        "src/main/resources/static/lib/sb-admin/app/js/sb-admin-2.js",
        "src/main/resources/static/lib/angular-loading-bar/src/loading-bar.js"
    ],
    css_all: [
        "src/main/resources/static/lib/bootstrap/dist/css/bootstrap.min.css",
        "src/main/resources/static/lib/metisMenu/dist/metisMenu.min.css",
        "src/main/resources/static/lib/sb-admin/app/styles/sb-admin-2.css",
        "src/main/resources/static/lib/font-awesome/css/font-awesome.min.css",
        "src/main/resources/static/lib/datatables/media/css/dataTables.bootstrap.css",
        "src/main/resources/static/lib/angular-loading-bar/src/loading-bar.css"
    ],
    img: ['src/main/resources/static/assets/imgs/*'],
    font: ['src/main/resources/static/lib/font-awesome/fonts/*',
        'src/main/resources/static/lib/bootstrap/dist/fonts/*'],
    clear: [
        'dist/*'
    ]
};

gulp.task('clean', function () {
        return gulp.src(buildConfig.clear)
            .pipe(clean({force: true}));
    }
);


gulp.task('install', function () {
    return bower.commands.install()
        .on('log', function (data) {
            gutil.log('bower', gutil.colors.cyan(data.id), data.message);
        });
});


// Styles
gulp.task('styles', function () {
    return gulp.src(buildConfig.css_all)
        .pipe(minifycss())
        .pipe(concat("all.min.css"))
        .pipe(gulp.dest('dist/assets/css'));
});


// Libs
gulp.task('libs', function () {
    return gulp.src(buildConfig.js_lib)
        .pipe(concat('lib.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('dist/lib'));
});

// Scripts
gulp.task('scripts', function () {
    return gulp.src(buildConfig.js_all)
        .pipe(ngAnnotate())
        .pipe(concat('all.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest('dist/scripts'));
});

// Templates
gulp.task('templates', function () {
    gulp.src(buildConfig.template_all)
        .pipe(templateCache({
            standalone: true,
            root: 'templates'
        }))
        .pipe(gulp.dest('dist/scripts'));

});


// Assets
gulp.task('assets', function () {
    return gulp.src(buildConfig.img)
        .pipe(cache(imagemin({optimizationLevel: 3, progressive: true, interlaced: true})))
        .pipe(gulp.dest('dist/assets/imgs'));
});

// Fonts
gulp.task('fonts', function () {
    return gulp.src(buildConfig.font)
        .pipe(gulp.dest('dist/assets/fonts'));
});


gulp.task("copyIndex", function () {
    return gulp.src("template.html")
        .pipe(rename("index.html"))
        .pipe(gulp.dest("dist"));

})


// Default task
gulp.task('default', ['clean'], function () {
    gulp.start('styles', 'libs', 'scripts', 'templates', 'assets', 'fonts', 'copyIndex');
});


