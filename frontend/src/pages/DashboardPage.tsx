import { Link } from 'react-router-dom';
import { Users, GraduationCap, School, UserCheck, BookOpen, ClipboardList, Award } from 'lucide-react';

const stats = [
  { label: 'Usuarios', icon: Users, count: '--', color: 'bg-blue-500', path: '/users' },
  { label: 'Estudiantes', icon: GraduationCap, count: '--', color: 'bg-green-500', path: '/students' },
  { label: 'Docentes', icon: School, count: '--', color: 'bg-purple-500', path: '/teachers' },
  { label: 'Apoderados', icon: UserCheck, count: '--', color: 'bg-yellow-500', path: '/guardians' },
  { label: 'Cursos', icon: BookOpen, count: '--', color: 'bg-red-500', path: '/courses' },
  { label: 'Evaluaciones', icon: ClipboardList, count: '--', color: 'bg-indigo-500', path: '/evaluations' },
  { label: 'Notas', icon: Award, count: '--', color: 'bg-pink-500', path: '/grades' },
];

export default function DashboardPage() {
  return (
    <div>
      <h1 className="text-2xl font-bold text-gray-900 mb-6">Dashboard</h1>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4">
        {stats.map((stat) => (
          <Link
            key={stat.label}
            to={stat.path}
            className="bg-white rounded-lg shadow p-6 hover:shadow-lg transition-shadow"
          >
            <div className="flex items-center">
              <div className={`p-3 rounded-lg ${stat.color}`}>
                <stat.icon className="w-6 h-6 text-white" />
              </div>
              <div className="ml-4">
                <p className="text-sm font-medium text-gray-500">{stat.label}</p>
                <p className="text-2xl font-bold text-gray-900">{stat.count}</p>
              </div>
            </div>
          </Link>
        ))}
      </div>

      <div className="mt-8 bg-white rounded-lg shadow p-6">
        <h2 className="text-lg font-semibold text-gray-900 mb-4">Bienvenido a SmartBook</h2>
        <p className="text-gray-600">
          Selecciona una sección del menú lateral para comenzar a gestionar la plataforma educativa.
        </p>
      </div>
    </div>
  );
}
