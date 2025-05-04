import os
import sys
import argparse
from pathlib import Path
from string import Template

build_template = Template("""
import visify.tools.androidModule
import visify.tools.impl
import visify.tools.Plugins
import visify.tools.visifyKapt

androidModule(
    pkg = "${pkg}",
    useCompose = ${use_compose},
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt
    ),
    deps = {
        impl(libs.googleDagger.hiltAndroid)
        visifyKapt(libs.googleDagger.hiltCompiler)
    }
)
""".strip())

gitignore_template = """
/build
""".strip()

manifest_template = """
<?xml version="1.0" encoding="utf-8"?><manifest/>
""".strip()

settings_template = Template("""
include("${pkg}")
""".strip())

def create_dir(path):
    path.mkdir(parents=True, exist_ok=True)

def check_module_exists(path, dirs):
    current_path = Path(path)
    for directory in dirs:
        current_path = current_path / directory
    if current_path.exists():
        raise FileExistsError(f"The directory '{current_path}' already exists.")


def create_directories(base_path, gradle_path):
    directories = [part for part in gradle_path.split(':') if part]
    check_module_exists(base_path, directories)
    for directory in directories:
        base_path = Path(base_path) / directory
        create_dir(base_path)

def create_file_with_content(file_path, content):
    with open(file_path, 'w') as file:
        file.write(content)

def append_to_file(file_path, content):
    with open(file_path, 'a') as file:
        file.write(content)

def create_build_file(path, pkg, useCompose):
    content = build_template.substitute(pkg=pkg, use_compose="true" if useCompose else "false")
    create_file_with_content(Path(path) / "build.gradle.kts", content)

def create_gitignore_file(path):
    create_file_with_content(Path(path) / ".gitignore", gitignore_template)

def create_src_with_pkg(path, pkg):
    dirs = pkg.split('.')
    base_path = Path(path)
    src_path = base_path / "src"
    create_dir(src_path)
    main_path = src_path / "main"
    create_dir(main_path)
    java_path = main_path / "java"
    create_dir(java_path)
    pkg_dir = java_path
    for directory in dirs:
        pkg_dir = pkg_dir / directory
        create_dir(pkg_dir)
    create_file_with_content(main_path / "AndroidManifest.xml", manifest_template)

def modify_settings_file(project, module):
    settings_path = Path(project) / "settings.gradle.kts"
    content = settings_template.substitute(pkg=module)
    append_to_file(settings_path, '\n' + content)


def create_visify_module(project_dir, gradle_path, useCompose):
    # create module directories
    create_directories(project_dir, gradle_path)

    # get created module path
    module_path = project_dir + gradle_path.replace(':', os.sep)
    # android module prefix
    module_prefix = "care.visify"
    # actual module package
    module_pkg = module_prefix + gradle_path.replace(':', '.')

    # build.gradle.kts file
    create_build_file(module_path, module_pkg, useCompose)
    # gitignore file
    create_gitignore_file(module_path)
    # create src files + package directories
    create_src_with_pkg(module_path, module_pkg)
    # add created module to settings file
    modify_settings_file(project_dir, gradle_path)


def parse_arguments():
    # Create the parser
    parser = argparse.ArgumentParser(description="Process project parameters.")

    # Add arguments
    parser.add_argument('--proj-path', type=str, help='Path to the project directory', required=True)
    parser.add_argument('--gradle-name', type=str, help='Name of the module', required=True)
    parser.add_argument('--useCompose', action='store_true', help='Flag to indicate if Jetpack Compose should be used')

    # Parse arguments
    args = parser.parse_args()

    return args

if __name__ == "__main__":
    args = parse_arguments()
    create_visify_module(args.proj_path, args.gradle_name, args.useCompose)