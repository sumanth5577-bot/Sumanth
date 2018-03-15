module.exports = function(grunt) {
  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    watch: {
        less: {
            files: ['web/webroot/WEB-INF/_ui-src/shared/less/variableMapping.less','web/webroot/WEB-INF/_ui-src/shared/less/generatedVariables.less',
                    'web/webroot/WEB-INF/_ui-src/responsive/lib/ybase-*/less/*', 'web/webroot/WEB-INF/_ui-src/**/themes/**/less/*.less'],
            tasks: ['less'],
        },
        fonts: {
            files: ['web/webroot/WEB-INF/_ui-src/**/themes/**/fonts/*'],
            tasks: ['sync:syncfonts'],
        },
        ybasejs: {
            files: ['web/webroot/WEB-INF/_ui-src/responsive/lib/ybase-0.1.0/js/**/*.js'],
            tasks: ['sync:syncybase'],
        },
        jquery: {
            files: ['web/webroot/WEB-INF/_ui-src/responsive/lib/jquery*.js'],
            tasks: ['sync:syncjquery'],
        },
    },
    
 less: { 
 
  knack1: {
            files: [
                {
                    expand: true,
                    cwd: 'web/webroot/WEB-INF/_ui-src/',
     src: '**/themes/**/less/knackstyleheader1.less',
                    dest: 'web/webroot/_ui/',
                    ext: '.css',
                    rename:function(dest,src){
                       var nsrc = src.replace(new RegExp("/themes/(.*)/less"),"/theme-$1/css");
                       return dest+nsrc;
                    }
   
                }
            ]
        },
  
  knack2: {
            files: [
                {
                    expand: true,
                    cwd: 'web/webroot/WEB-INF/_ui-src/',
     src: '**/themes/**/less/knackstyleheader2.less',
                    dest: 'web/webroot/_ui/',
                    ext: '.css',
                    rename:function(dest,src){
                       var nsrc = src.replace(new RegExp("/themes/(.*)/less"),"/theme-$1/css");
                       return dest+nsrc;
                    }
   
                }
            ]
        },
  
  knack3: {
            files: [
                {
                    expand: true,
                    cwd: 'web/webroot/WEB-INF/_ui-src/',
     src: '**/themes/**/less/knackstyleheader3.less',
                    dest: 'web/webroot/_ui/',
                    ext: '.css',
                    rename:function(dest,src){
                       var nsrc = src.replace(new RegExp("/themes/(.*)/less"),"/theme-$1/css");
                       return dest+nsrc;
                    }
   
                }
            ]
        },
  
  knack4: {
            files: [
                {
                    expand: true,
                    cwd: 'web/webroot/WEB-INF/_ui-src/',
     src: '**/themes/**/less/knackstyleheader4.less',
                    dest: 'web/webroot/_ui/',
                    ext: '.css',
                    rename:function(dest,src){
                       var nsrc = src.replace(new RegExp("/themes/(.*)/less"),"/theme-$1/css");
                       return dest+nsrc;
                    }
   
                }
            ]
        },
  
  knack5: {
            files: [
                {
                    expand: true,
                    cwd: 'web/webroot/WEB-INF/_ui-src/',
     src: '**/themes/**/less/knackstyleheader5.less',
                    dest: 'web/webroot/_ui/',
                    ext: '.css',
                    rename:function(dest,src){
                       var nsrc = src.replace(new RegExp("/themes/(.*)/less"),"/theme-$1/css");
                       return dest+nsrc;
                    }
   
                }
            ]
        },
  
  default: {
            files: [
                {
                    expand: true,
                    cwd: 'web/webroot/WEB-INF/_ui-src/',
     src: '**/themes/**/less/style.less',
                    dest: 'web/webroot/_ui/',
                    ext: '.css',
                    rename:function(dest,src){
                       var nsrc = src.replace(new RegExp("/themes/(.*)/less"),"/theme-$1/css");
                       return dest+nsrc;
                    }
   
                }
            ]
        },
    },

    sync : {
     syncfonts: {
      files: [{
                expand: true,
       cwd: 'web/webroot/WEB-INF/_ui-src/',
       src: '**/themes/**/fonts/*',
       dest: 'web/webroot/_ui/',
       rename:function(dest,src){
                 var nsrc = src.replace(new RegExp("/themes/(.*)"),"/theme-$1");
                 return dest+nsrc;
             }
      }]
     },
     syncybase: {
      files: [{
       cwd: 'web/webroot/WEB-INF/_ui-src/responsive/lib/ybase-0.1.0/js/',
       src: '**/*.js',
       dest: 'web/webroot/_ui/responsive/common/js',
      }]
     },
     syncjquery: {
      files: [{
       cwd: 'web/webroot/WEB-INF/_ui-src/responsive/lib',
       src: 'jquery*.js',
       dest: 'web/webroot/_ui/responsive/common/js',
      }]
     }
    }
    
});
 
  // Plugins
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-less');
  grunt.loadNpmTasks('grunt-sync');

  // Knack task(s).
  grunt.registerTask('knack1', ['less', 'sync']);
  grunt.registerTask('knack2', ['less', 'sync']);
  grunt.registerTask('knack3', ['less', 'sync']);
  grunt.registerTask('knack4', ['less', 'sync']);
  grunt.registerTask('knack5', ['less', 'sync']);
  // Default task(s).
  grunt.registerTask('default', ['less', 'sync']);
  
};